import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class PanelBView extends JPanel {
    private JPanel pContent;
    private JScrollPane sMain;
    private JButton bt_Submit;

    private ArrayList<String> viewCustomer = new ArrayList<>();
    private ArrayList<String> viewRates = new ArrayList<>();
    private ArrayList<String> viewReply = new ArrayList<>();

    private JPanel pMain = new JPanel();

    public PanelBView() {
        cButton();
        cComponent();
        cLayOut();
    }

    private void cComponent() {      
        viewCustomer.add("Coustomer 1");
        viewCustomer.add("Coustomer 2");
        viewCustomer.add("Coustomer 3");
        viewCustomer.add("Coustomer 4");
        viewCustomer.add("Coustomer 5");
        //TODO: change to actual data;
        
        viewRates.add("Review 1");
        viewRates.add("Review 2");
        viewRates.add("Review 3");
        viewRates.add("Review 4");
        viewRates.add("Review 5");
        //TODO: change to actual data;

        for (String s:viewCustomer) {
            viewReply.add(null);
        }

        pContent = new JPanel();
        pContent.setLayout(new BoxLayout(pContent, BoxLayout.Y_AXIS));
        for (int i = 0; i < viewCustomer.size(); i++) {
            JPanel pTemp = new JPanel();
            pTemp.setLayout(new BorderLayout(5, 0));

            JLabel lblCustomer = new JLabel(viewCustomer.get(i) + ": ");
            JLabel lblRates = new JLabel(viewRates.get(i));
            JLabel lblReply = new JLabel();

            JPanel pRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
            JPanel pLeft = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
            JPanel pCenter = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));

            JTextField ta_Modify = new JTextField(20);
            JButton bt_Modify = new JButton("Reply");

            int index = i;

            bt_Modify.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    String content = ta_Modify.getText();
                    viewReply.set(index, content); 
                    lblReply.setText(content);
                }
            });

            bt_Modify.setPreferredSize(new Dimension(80,40));

            pLeft.add(lblCustomer);
            pLeft.add(lblRates);
            
            pRight.add(ta_Modify);
            pRight.add(bt_Modify);

            pCenter.add(new JLabel("Your reply: "));
            pCenter.add(lblReply);

            pTemp.add(pLeft, BorderLayout.WEST);
            pTemp.add(pCenter, BorderLayout.CENTER);
            pTemp.add(pRight, BorderLayout.EAST);

            pTemp.setAlignmentX(Component.LEFT_ALIGNMENT);
            pContent.add(pTemp);
        }

        sMain = new JScrollPane(pContent);
        sMain.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sMain.setPreferredSize(new Dimension(800,500));
    }
    private void cButton() {
        bt_Submit = new JButton("Submit");
        bt_Submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                int reply = JOptionPane.showConfirmDialog(pMain, "Submit?", "Submitting...", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    //TODO: update database
                    
                    JOptionPane.showMessageDialog(null, "Success!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    return;
                }
            }
        });
    }
    private void cLayOut() {
        setLayout(new BorderLayout());
        
        pMain.add(sMain, BorderLayout.CENTER);
        pMain.add(bt_Submit, BorderLayout.SOUTH);

        add(pMain);
    }
}
