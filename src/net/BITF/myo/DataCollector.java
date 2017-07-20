package net.BITF.myo;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;

import net.BITF.frame.MainFrame;
import net.BITF.panel.GamePanel;

import com.thalmic.myo.AbstractDeviceListener;
import com.thalmic.myo.Myo;
import com.thalmic.myo.Pose;
import com.thalmic.myo.Quaternion;
import com.thalmic.myo.enums.Arm;
import com.thalmic.myo.enums.PoseType;
import com.thalmic.myo.enums.VibrationType;
import com.thalmic.myo.enums.WarmupState;
import com.thalmic.myo.enums.XDirection;

public class DataCollector extends AbstractDeviceListener {
	private static final int SCALE = 18;

	protected MainFrame mainFrame;

	private double rollW;


	private double pitchW;
	private double yawW;
	private Pose currentPose;
	private Arm whichArm;

	private Robot robot;

	public boolean flag = false;

	public boolean pullflag = false;
	public boolean nameflag = false;

	public DataCollector(MainFrame mainFrame) {

		this.mainFrame = mainFrame;

		rollW = 0;
		pitchW = 0;
		yawW = 0;
		currentPose = new Pose();

		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	@Override
	public void onOrientationData(Myo myo, long timestamp, Quaternion rotation) {
		Quaternion normalized = rotation.normalized();

		double roll = Math.atan2(2.0f * (normalized.getW() * normalized.getX() + normalized.getY() * normalized.getZ()), 1.0f - 2.0f * (normalized.getX() * normalized.getX() + normalized.getY() * normalized.getY()));
		double pitch = Math.asin(2.0f * (normalized.getW() * normalized.getY() - normalized.getZ() * normalized.getX()));
		double yaw = Math.atan2(2.0f * (normalized.getW() * normalized.getZ() + normalized.getX() * normalized.getY()), 1.0f - 2.0f * (normalized.getY() * normalized.getY() + normalized.getZ() * normalized.getZ()));

		rollW = ((roll + Math.PI) / (Math.PI * 2.0) * SCALE);
		pitchW = ((pitch + Math.PI / 2.0) / Math.PI * SCALE);
		yawW = ((yaw + Math.PI) / (Math.PI * 2.0) * SCALE);
	}

	@Override
	public void onPose(Myo myo, long timestamp, Pose pose) {

		Pose oldPose = currentPose;
		currentPose = pose;

		if (currentPose.getType() ==  PoseType.FIST) {
			myo.vibrate(VibrationType.VIBRATION_MEDIUM);
		}

		flag=false;
		switch(currentPose.getType()){
		case FIST:
//			robot.mousePress(InputEvent.BUTTON1_MASK);
//			robot.mouseRelease(InputEvent.BUTTON1_MASK);

			break;
		case FINGERS_SPREAD:
			if(MainFrame.stage == 1 && pullflag == false){
				flag=true;
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
			}
			else if(mainFrame.stage == 1 && pullflag == true){
				//なにもしない
			}
			else{
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
			}
			break;
		case WAVE_OUT:
			if(MainFrame.stage == 1 && pullflag == false){//gamestage
				MyoControl.x=1200;
				MyoControl.y=70;
				robot.mouseMove(1200, 70);
				MyoControl.point.setLocation(1200, 70);
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
				pullflag=true;
			}
			if(MainFrame.stage==0 && nameflag == false){//startstage
				MyoControl.x=560;
				MyoControl.y=510;
				robot.mouseMove(560, 510);
				MyoControl.point.setLocation(560, 510);
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);
				nameflag=true;
			}
			break;
		case REST:
			if(MainFrame.stage == 1 && pullflag == false){
				GamePanel panel = (GamePanel) mainFrame.getPanel();
//				robot.mousePress(InputEvent.BUTTON1_MASK);
//				robot.mouseRelease(InputEvent.BUTTON1_MASK);
				panel.answer();
				}
			break;
		case WAVE_IN:

			if(MainFrame.stage == 1 && pullflag == true){//gamestage
				GamePanel panel = (GamePanel) mainFrame.getPanel();
				robot.mousePress(InputEvent.BUTTON1_MASK);
				robot.mouseRelease(InputEvent.BUTTON1_MASK);

				MyoControl.x=683;
				MyoControl.y=384;
				robot.mouseMove(683, 384);
				MyoControl.point.setLocation(683, 384);
				pullflag= false;
			}

			if(MainFrame.stage==2){//endstage
				MainFrame.stage = 0;

				mainFrame.nextStage();
				nameflag= false;
			}
			if(MainFrame.stage==0 && nameflag == true){//startstage
				MainFrame.stage = 1;
				mainFrame.nextStage();
			}

			break;

		default:
			break;
		}
	}

	@Override
	public void onArmSync(Myo myo, long timestamp, Arm arm, XDirection xDirection, float rotation, WarmupState warmupState) {
		whichArm = arm;
	}

	@Override
	public void onArmUnsync(Myo myo, long timestamp) {
		whichArm = null;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder("\r");

		String xDisplay = String.format("[%s%s]", repeatCharacter('*', (int) rollW), repeatCharacter(' ', (int) (SCALE - rollW)));
		String yDisplay = String.format("[%s%s]", repeatCharacter('*', (int) pitchW), repeatCharacter(' ', (int) (SCALE - pitchW)));
		String zDisplay = String.format("[%s%s]", repeatCharacter('*', (int) yawW), repeatCharacter(' ', (int) (SCALE - yawW)));

		String armString = null;
		if (whichArm != null) {
			armString = String.format("[%s]", whichArm == Arm.ARM_LEFT ? "L" : "R");
		} else {
			armString = String.format("[?]");
		}
		String poseString = null;
		if (currentPose != null) {
			String poseTypeString = currentPose.getType().toString();
			poseString = String.format("[%s%" + (SCALE - poseTypeString.length()) + "s]", poseTypeString, " ");
		} else {
			poseString = String.format("[%14s]", " ");
		}
		builder.append(xDisplay);
		builder.append(yDisplay);
		builder.append(zDisplay);
		builder.append(armString);
		builder.append(poseString);

		return builder.toString();
	}

	private String repeatCharacter(char character, int numOfTimes) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < numOfTimes; i++) {
			builder.append(character);
		}
		return builder.toString();
	}



	public double getRollW() {
		return rollW;
	}

	public void setRollW(double rollW) {
		this.rollW = rollW;
	}

	public double getPitchW() {
		return pitchW;
	}

	public void setPitchW(double pitchW) {
		this.pitchW = pitchW;
	}

	public double getYawW() {
		return yawW;
	}

	public void setYawW(double yawW) {
		this.yawW = yawW;
	}

	public Pose getCurrentPose() {
		return currentPose;
	}

	public Robot getRobot() {
		return robot;
	}

}
