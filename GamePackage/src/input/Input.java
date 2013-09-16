package input;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Input
{	
	//boolean values determine if key is pressed
	public static boolean A,B,C,D,E,F,G,H,I,J,K,L,M,N,O,P,Q,R,S,T,U,V,W,X,Y,Z;
	public static boolean NUMPAD1,NUMPAD2,NUMPAD3,NUMPAD4,NUMPAD5,NUMPAD6,NUMPAD7,NUMPAD8,NUMPAD9,NUMPAD0;
	public static boolean NUMBER1,NUMBER2,NUMBER3,NUMBER4,NUMBER5,NUMBER6,NUMBER7,NUMBER8,NUMBER9,NUMBER0;
	public static boolean UP,DOWN,LEFT,RIGHT,ESCAPE,SPACE,BACKSPACE;

	//mouse info
	public static boolean MOUSE_LEFT	= false;
	public static boolean MOUSE_RIGHT	= false;

	public static int MOUSE_X	= 0;
	public static int MOUSE_Y	= 0;
	
	public static boolean keyChange(KeyEvent e, boolean newKeyState)
	{		//changes key state variables to correct values
		int key = e.getKeyCode();
		
		switch(key)
		{
		case KeyEvent.VK_A:
			A = newKeyState;
			break;
		case KeyEvent.VK_B:
			B = newKeyState;
			break;
		case KeyEvent.VK_C:
			C = newKeyState;
			break;
		case KeyEvent.VK_D:
			D = newKeyState;
			break;
		case KeyEvent.VK_E:
			E = newKeyState;
			break;
		case KeyEvent.VK_F:
			F = newKeyState;
			break;
		case KeyEvent.VK_G:
			G = newKeyState;
			break;
		case KeyEvent.VK_H:
			H = newKeyState;
			break;
		case KeyEvent.VK_I:
			I = newKeyState;
			break;
		case KeyEvent.VK_J:
			J = newKeyState;
			break;
		case KeyEvent.VK_K:
			K = newKeyState;
			break;
		case KeyEvent.VK_L:
			L = newKeyState;
			break;
		case KeyEvent.VK_M:
			M = newKeyState;
			break;
		case KeyEvent.VK_N:
			N = newKeyState;
			break;
		case KeyEvent.VK_O:
			O = newKeyState;
			break;
		case KeyEvent.VK_P:
			P = newKeyState;
			break;
		case KeyEvent.VK_Q:
			Q = newKeyState;
			break;
		case KeyEvent.VK_R:
			R = newKeyState;
			break;
		case KeyEvent.VK_S:
			S = newKeyState;
			break;
		case KeyEvent.VK_T:
			T = newKeyState;
			break;
		case KeyEvent.VK_U:
			U = newKeyState;
			break;
		case KeyEvent.VK_V:
			V = newKeyState;
			break;
		case KeyEvent.VK_W:
			W = newKeyState;
			break;
		case KeyEvent.VK_X:
			X = newKeyState;
			break;
		case KeyEvent.VK_Y:
			Y = newKeyState;
			break;
		case KeyEvent.VK_Z:
			Z = newKeyState;
			break;
			
		case KeyEvent.VK_UP:
			UP = newKeyState;
			break;
		case KeyEvent.VK_DOWN:
			DOWN = newKeyState;
			break;
		case KeyEvent.VK_LEFT:
			LEFT = newKeyState;
			break;
		case KeyEvent.VK_RIGHT:
			RIGHT = newKeyState;
			break;
		
		case KeyEvent.VK_ESCAPE:
			ESCAPE = newKeyState;
			break;
		case KeyEvent.VK_SPACE:
			SPACE = newKeyState;
			break;
		case KeyEvent.VK_BACK_SPACE:
			BACKSPACE = newKeyState;
			break;
			
		case KeyEvent.VK_NUMPAD0:
			NUMPAD0 = newKeyState;
			break;
		case KeyEvent.VK_NUMPAD1:
			NUMPAD1 = newKeyState;
			break;
		case KeyEvent.VK_NUMPAD2:
			NUMPAD2 = newKeyState;
			break;
		case KeyEvent.VK_NUMPAD3:
			NUMPAD3 = newKeyState;
			break;
		case KeyEvent.VK_NUMPAD4:
			NUMPAD4 = newKeyState;
			break;
		case KeyEvent.VK_NUMPAD5:
			NUMPAD5 = newKeyState;
			break;
		case KeyEvent.VK_NUMPAD6:
			NUMPAD6 = newKeyState;
			break;
		case KeyEvent.VK_NUMPAD7:
			NUMPAD7 = newKeyState;
			break;
		case KeyEvent.VK_NUMPAD8:
			NUMPAD8 = newKeyState;
			break;
		case KeyEvent.VK_NUMPAD9:
			NUMPAD9 = newKeyState;
			break;
			
		case KeyEvent.VK_0:
			NUMBER0 = newKeyState;
			break;
		case KeyEvent.VK_1:
			NUMBER1 = newKeyState;
			break;
		case KeyEvent.VK_2:
			NUMBER2 = newKeyState;
			break;
		case KeyEvent.VK_3:
			NUMBER3 = newKeyState;
			break;
		case KeyEvent.VK_4:
			NUMBER4 = newKeyState;
			break;
		case KeyEvent.VK_5:
			NUMBER5 = newKeyState;
			break;
		case KeyEvent.VK_6:
			NUMBER6 = newKeyState;
			break;
		case KeyEvent.VK_7:
			NUMBER7 = newKeyState;
			break;
		case KeyEvent.VK_8:
			NUMBER8 = newKeyState;
			break;
		case KeyEvent.VK_9:
			NUMBER9 = newKeyState;
			break;
		}
		
		return true;
	}
	
	public static boolean mouseUpdate(int x, int y)	//handles mouse movement
	{
		Input.MOUSE_X = x;
		Input.MOUSE_Y = y;
		return true;
	}
	
	public static boolean mouseChange(MouseEvent e, boolean newKeyState)	//handles mouse clicks
	{
		if(e.getButton() == MouseEvent.BUTTON1)
			Input.MOUSE_LEFT	= newKeyState;
		if(e.getButton() == MouseEvent.BUTTON3)
			Input.MOUSE_RIGHT	= newKeyState;
		return true;
	}
}