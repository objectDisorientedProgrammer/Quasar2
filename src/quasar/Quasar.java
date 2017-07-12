// Copyright (c) 2013 Gamma (Douglas Chidester, James Howard, Steve Corbette)

package quasar;

public class Quasar
{
	public static void main(String[] args)
	{
		javax.swing.SwingUtilities.invokeLater(new Runnable()
		{
            public void run()
            {
            	new MainWindow();
            }
        });
	}
}