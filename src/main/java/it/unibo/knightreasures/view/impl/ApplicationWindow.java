public class ApplicationWindow {

    private JFrame frame;

    public ApplicationWindow(ApplicationPanel applicationPanel) {

        frame = new JFrame();
        frame.setSize(1280, 800);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(applicationPanel);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }

}
