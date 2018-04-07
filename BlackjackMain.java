import javax.swing.JFrame;

public class BlackjackMain
{

	public static void main( String args[] )
	{
		BlackjackModel model = new BlackjackModel();
		BlackjackView view = new BlackjackView(model);
		BlackjackController controller = new BlackjackController( model, view );
		
		
		view.addListener(controller);
		view.setVisible(true);
		view.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}