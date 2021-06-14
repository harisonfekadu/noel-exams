package uiComponents;

import javafx.scene.control.Label;
public class Timer extends Label{
	private int second;
	private int minute;
	public Timer (double limit){
		minute=(int)limit;
		second=(int)(limit-minute)*60;
		this.set();
	}
	public void count(){
		if(--second<0){
			if(--minute<0){
				minute=0;
			}
		}
		this.set();
	}
	private void set(){
		if(second<10&&minute<10){
			this.setText("0"+minute+":0"+second);
		} else if(second<10&&minute>=10){
			this.setText(minute+":0"+second);
		}  else if(second>=10&&minute<10){
			this.setText("0"+minute+":"+second);
		} else {
			this.setText(minute+":"+second);
		}
	}
}