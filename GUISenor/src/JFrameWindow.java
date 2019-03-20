/*     */ 
/*     */ 
/*     */ import java.awt.Color;
/*     */ import java.awt.Graphics2D;
/*     */ import java.awt.event.ActionEvent;
/*     */ import javax.swing.BorderFactory;
/*     */ import javax.swing.GroupLayout;
/*     */ import javax.swing.GroupLayout.Alignment;
/*     */ import javax.swing.GroupLayout.ParallelGroup;
/*     */ import javax.swing.GroupLayout.SequentialGroup;
/*     */ import javax.swing.JComboBox;
/*     */ import javax.swing.JPanel;
import javax.swing.LayoutStyle;
/*     */ import javax.swing.LayoutStyle.ComponentPlacement;
/*     */ 
/*     */ public class JFrameWindow extends javax.swing.JFrame
/*     */ {
/*     */   private JComboBox jComboBox_controllers;
/*     */   private javax.swing.JLabel jLabelXYAxis;
/*     */   private JPanel jPanelAxes;
/*     */   private JPanel jPanelButtons;
/*     */   private JPanel jPanelHatSwitch;
/*     */   private JPanel jPanelXYAxis;
/*     */   private JPanel jPanel_forAxis;
/*     */   
/*     */   public JFrameWindow()
/*     */   {
/*     */     try
/*     */     {
/*  29 */       javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
/*     */     } catch (ClassNotFoundException|InstantiationException|IllegalAccessException|javax.swing.UnsupportedLookAndFeelException e) {
/*  31 */       e.printStackTrace();
/*     */     }
/*     */     
/*  34 */     initComponents();
/*     */     
/*  36 */     setResizable(false);
/*  37 */     setLocationRelativeTo(null);
/*  38 */     setVisible(true);
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   private void initComponents()
/*     */   {
/*  50 */     this.jPanelAxes = new JPanel();
/*  51 */     this.jLabelXYAxis = new javax.swing.JLabel();
/*  52 */     this.jPanelXYAxis = new JPanel();
/*  53 */     this.jPanel_forAxis = new JPanel();
/*  54 */     this.jPanelButtons = new JPanel();
/*  55 */     this.jPanelHatSwitch = new JPanel();
/*  56 */     this.jComboBox_controllers = new JComboBox();
/*     */     
/*  58 */     setDefaultCloseOperation(3);
/*  59 */     setTitle("JInput Joystick Test");
/*     */     
/*  61 */     this.jPanelAxes.setBorder(BorderFactory.createTitledBorder(null, "Axes", 0, 0, null, new Color(0, 51, 204)));
/*     */     
/*  63 */     this.jLabelXYAxis.setText("X Axis / Y Axis");
/*     */     
/*  65 */     this.jPanelXYAxis.setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
/*  66 */     this.jPanelXYAxis.setPreferredSize(new java.awt.Dimension(111, 111));
/*     */     
/*  68 */     GroupLayout jPanelXYAxisLayout = new GroupLayout(this.jPanelXYAxis);
/*  69 */     this.jPanelXYAxis.setLayout(jPanelXYAxisLayout);
/*  70 */     jPanelXYAxisLayout.setHorizontalGroup(jPanelXYAxisLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 109, 32767));
/*     */     
/*     */ 
/*     */ 
/*  74 */     jPanelXYAxisLayout.setVerticalGroup(jPanelXYAxisLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 109, 32767));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  79 */     GroupLayout jPanel_forAxisLayout = new GroupLayout(this.jPanel_forAxis);
/*  80 */     this.jPanel_forAxis.setLayout(jPanel_forAxisLayout);
/*  81 */     jPanel_forAxisLayout.setHorizontalGroup(jPanel_forAxisLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 202, 32767));
/*     */     
/*     */ 
/*     */ 
/*  85 */     jPanel_forAxisLayout.setVerticalGroup(jPanel_forAxisLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 0, 32767));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*  90 */     GroupLayout jPanelAxesLayout = new GroupLayout(this.jPanelAxes);
/*  91 */     this.jPanelAxes.setLayout(jPanelAxesLayout);
/*  92 */     jPanelAxesLayout.setHorizontalGroup(jPanelAxesLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanelAxesLayout.createSequentialGroup().addGroup(jPanelAxesLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanelAxesLayout.createSequentialGroup().addGap(58, 58, 58).addComponent(this.jLabelXYAxis)).addGroup(jPanelAxesLayout.createSequentialGroup().addGap(37, 37, 37).addComponent(this.jPanelXYAxis, -2, -1, -2))).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.jPanel_forAxis, -2, -1, -2).addContainerGap()));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 106 */     jPanelAxesLayout.setVerticalGroup(jPanelAxesLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanelAxesLayout.createSequentialGroup().addComponent(this.jLabelXYAxis).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanelXYAxis, -2, -1, -2).addGap(0, 16, 32767)).addComponent(this.jPanel_forAxis, -1, -1, 32767));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 116 */     this.jPanelButtons.setBorder(BorderFactory.createTitledBorder(null, "Buttons", 0, 0, null, new Color(0, 51, 204)));
/*     */     
/* 118 */     GroupLayout jPanelButtonsLayout = new GroupLayout(this.jPanelButtons);
/* 119 */     this.jPanelButtons.setLayout(jPanelButtonsLayout);
/* 120 */     jPanelButtonsLayout.setHorizontalGroup(jPanelButtonsLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 248, 32767));
/*     */     
/*     */ 
/*     */ 
/* 124 */     jPanelButtonsLayout.setVerticalGroup(jPanelButtonsLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 112, 32767));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 129 */     this.jPanelHatSwitch.setBorder(BorderFactory.createTitledBorder(null, "Hat Switch", 0, 0, null, new Color(0, 51, 204)));
/*     */     
/* 131 */     GroupLayout jPanelHatSwitchLayout = new GroupLayout(this.jPanelHatSwitch);
/* 132 */     this.jPanelHatSwitch.setLayout(jPanelHatSwitchLayout);
/* 133 */     jPanelHatSwitchLayout.setHorizontalGroup(jPanelHatSwitchLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 121, 32767));
/*     */     
/*     */ 
/*     */ 
/* 137 */     jPanelHatSwitchLayout.setVerticalGroup(jPanelHatSwitchLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 0, 32767));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/* 142 */     this.jComboBox_controllers.addActionListener(new java.awt.event.ActionListener() {
/*     */       public void actionPerformed(ActionEvent evt) {
/* 144 */         JFrameWindow.this.jComboBox_controllersActionPerformed(evt);
/*     */       }
/*     */       
/* 147 */     });
/* 148 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 149 */     getContentPane().setLayout(layout);
/* 150 */     layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addComponent(this.jPanelButtons, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jPanelHatSwitch, -1, -1, 32767)).addComponent(this.jPanelAxes, -1, -1, 32767)).addContainerGap()).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addGap(0, 0, 32767).addComponent(this.jComboBox_controllers, -2, 237, -2).addGap(88, 88, 88)))));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 168 */     layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(layout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(this.jComboBox_controllers, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED).addComponent(this.jPanelAxes, -2, -1, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.jPanelButtons, -1, -1, 32767).addComponent(this.jPanelHatSwitch, -1, -1, 32767)).addContainerGap()));
/*     */     
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/* 182 */     pack();
/*     */   }
/*     */   
/*     */   private void jComboBox_controllersActionPerformed(ActionEvent evt)
/*     */   {
/* 187 */     this.jPanelButtons.removeAll();
/* 188 */     this.jPanelButtons.repaint();
/* 189 */     this.jPanel_forAxis.removeAll();
/* 190 */     this.jPanel_forAxis.repaint();
/*     */   }
/*     */   
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   public int getSelectedControllerName()
/*     */   {
/* 209 */     return this.jComboBox_controllers.getSelectedIndex();
/*     */   }
/*     */   
/*     */   public void addControllerName(String controllerName) {
/* 213 */     this.jComboBox_controllers.addItem(controllerName);
/*     */   }
/*     */   
/*     */   public void showControllerDisconnected() {
/* 217 */     this.jComboBox_controllers.removeAllItems();
/* 218 */     this.jComboBox_controllers.addItem("Controller disconnected!");
/*     */   }
/*     */   
/*     */   public void setXYAxis(int xPercentage, int yPercentage) {
/* 222 */     Graphics2D g2d = (Graphics2D)this.jPanelXYAxis.getGraphics();
/* 223 */     g2d.clearRect(1, 1, this.jPanelXYAxis.getWidth() - 2, this.jPanelXYAxis.getHeight() - 2);
/* 224 */     g2d.fillOval(xPercentage, yPercentage, 10, 10);
/*     */   }
/*     */   
/*     */   public void setControllerButtons(JPanel buttonsPanel) {
/* 228 */     this.jPanelButtons.removeAll();
/* 229 */     this.jPanelButtons.add(buttonsPanel);
/* 230 */     this.jPanelButtons.validate();
/*     */   }
/*     */   
/*     */   public void setHatSwitch(float hatSwitchPosition) {
/* 234 */     int circleSize = 100;
/*     */     
/* 236 */     Graphics2D g2d = (Graphics2D)this.jPanelHatSwitch.getGraphics();
/* 237 */     g2d.clearRect(5, 15, this.jPanelHatSwitch.getWidth() - 10, this.jPanelHatSwitch.getHeight() - 22);
/* 238 */     g2d.drawOval(20, 22, circleSize, circleSize);
/*     */     
/* 240 */     if (Float.compare(hatSwitchPosition, 0.0F) == 0) {
/* 241 */       return;
/*     */     }
/* 243 */     int smallCircleSize = 10;
/* 244 */     int upCircleX = 65;
/* 245 */     int upCircleY = 17;
/* 246 */     int leftCircleX = 15;
/* 247 */     int leftCircleY = 68;
/* 248 */     int betweenX = 37;
/* 249 */     int betweenY = 17;
/*     */     
/* 251 */     int x = 0;
/* 252 */     int y = 0;
/*     */     
/* 254 */     g2d.setColor(Color.blue);
/*     */     
/* 256 */     if (Float.compare(hatSwitchPosition, 0.25F) == 0) {
/* 257 */       x = upCircleX;
/* 258 */       y = upCircleY;
/* 259 */     } else if (Float.compare(hatSwitchPosition, 0.75F) == 0) {
/* 260 */       x = upCircleX;
/* 261 */       y = upCircleY + circleSize;
/* 262 */     } else if (Float.compare(hatSwitchPosition, 1.0F) == 0) {
/* 263 */       x = leftCircleX;
/* 264 */       y = leftCircleY;
/* 265 */     } else if (Float.compare(hatSwitchPosition, 0.5F) == 0) {
/* 266 */       x = leftCircleX + circleSize;
/* 267 */       y = leftCircleY;
/* 268 */     } else if (Float.compare(hatSwitchPosition, 0.125F) == 0) {
/* 269 */       x = upCircleX - betweenX;
/* 270 */       y = upCircleY + betweenY;
/* 271 */     } else if (Float.compare(hatSwitchPosition, 0.375F) == 0) {
/* 272 */       x = upCircleX + betweenX;
/* 273 */       y = upCircleY + betweenY;
/* 274 */     } else if (Float.compare(hatSwitchPosition, 0.875F) == 0) {
/* 275 */       x = upCircleX - betweenX;
/* 276 */       y = upCircleY + circleSize - betweenY;
/* 277 */     } else if (Float.compare(hatSwitchPosition, 0.625F) == 0) {
/* 278 */       x = upCircleX + betweenX;
/* 279 */       y = upCircleY + circleSize - betweenY;
/*     */     }
/*     */     
/* 282 */     g2d.fillOval(x, y, smallCircleSize, smallCircleSize);
/*     */   }
/*     */   
/*     */   public void addAxisPanel(JPanel axesPanel) {
/* 286 */     this.jPanel_forAxis.removeAll();
/* 287 */     this.jPanel_forAxis.add(axesPanel);
/* 288 */     this.jPanel_forAxis.validate();
/*     */   }
/*     */ }


/* Location:              C:\Users\Shawn\Pictures\code\dist\JInputJoystickTestV2.jar!\jinputjoysticktestv2\JFrameWindow.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       0.7.1
 */