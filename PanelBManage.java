import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;

public class PanelBManage extends JPanel {
    private JLabel lTitle;
    private JPanel pContent,pTitle;
    private JScrollPane sMain;
    private JButton bt_Submit;

    private ArrayList<String> infoTitles = new ArrayList<>();
    private ArrayList<String> infoDetails = new ArrayList<>();

    private JPanel pMain = new JPanel();

    public PanelBManage() {
        cButton();
        cComponent();
        cLayOut();
    }

    private void cComponent() {
        lTitle = new JLabel("Test Bar name");
        lTitle.setFont(new Font("TimesRoman",Font.BOLD,30));
        //TODO: get from database
        pTitle = new JPanel();
        pTitle.add(lTitle);
        pTitle.setPreferredSize(new Dimension(500,100));
        
        infoTitles.add("Bar name");
        infoTitles.add("TA");
        infoTitles.add("Style");
        infoTitles.add("District");
        infoTitles.add("Open time");
        infoTitles.add("CloseTime");
        infoTitles.add("Featured Activity");
        //TODO: change to actual data;
        
        infoDetails.add("Test bar name");
        infoDetails.add("Test TA");
        infoDetails.add("Test Style");
        infoDetails.add("Test District");
        infoDetails.add("Test Open time");
        infoDetails.add("Test CloseTime");
        infoDetails.add("Test Featured Activity");
        //TODO: change to actual data;

        pContent = new JPanel();
        pContent.setLayout(new BoxLayout(pContent, BoxLayout.Y_AXIS));
        for (int i = 0; i < infoTitles.size(); i++) {
            JPanel pTemp = new JPanel();
            pTemp.setLayout(new BorderLayout(5, 0));

            JLabel lblTitle = new JLabel(infoTitles.get(i) + ": ");
            JLabel lblDetail = new JLabel(infoDetails.get(i));

            JPanel pRight = new JPanel(new FlowLayout(FlowLayout.RIGHT, 5, 0));
            JTextField ta_Modify = new JTextField(30);
            JButton bt_Modify = new JButton("Modify");

            int index = i;

            bt_Modify.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent event) {
                    String content = ta_Modify.getText();
                    infoDetails.set(index, content); 
                    lblDetail.setText(content);
                }
            });

            bt_Modify.setPreferredSize(new Dimension(80,40));

            pRight.add(ta_Modify);
            pRight.add(bt_Modify);

            pTemp.add(lblTitle, BorderLayout.WEST);
            pTemp.add(lblDetail, BorderLayout.CENTER);
            pTemp.add(pRight, BorderLayout.EAST);

            pTemp.setAlignmentX(Component.LEFT_ALIGNMENT);
            pContent.add(pTemp);
        }

        sMain = new JScrollPane(pContent);
        sMain.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        sMain.setPreferredSize(new Dimension(800,400));
    }
    private void cButton() {
        bt_Submit = new JButton("Submit");
        bt_Submit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                int reply = JOptionPane.showConfirmDialog(pMain, "Submit?", "Submitting...", JOptionPane.YES_NO_OPTION);
                if (reply == JOptionPane.YES_OPTION) {
                    //TODO: update database
                    ManageUpdateDB()
                    JOptionPane.showMessageDialog(null, "Success!", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    return;
                }
            }
        });
    }
    private void cLayOut() {
        setLayout(new BorderLayout());
        
        pMain.add(pTitle, BorderLayout.NORTH);
        pMain.add(sMain, BorderLayout.CENTER);
        pMain.add(bt_Submit, BorderLayout.SOUTH);

        add(pMain);
    }
}