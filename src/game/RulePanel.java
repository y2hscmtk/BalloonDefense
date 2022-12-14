package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

//규칙을 설명해주는 패널
public class RulePanel extends JPanel {
	private GameFrame parent;//부모를 변수로 저장
	
    private ImageIcon bgImageicon = new ImageIcon("background.png");
    private Image backgroundPanelImage = bgImageicon.getImage();
	
	//홈 버튼 아이콘
	private ImageIcon homeButtonIcon = new ImageIcon("home.png");
    private ImageIcon homeButtonEnteredIcon = new ImageIcon("homeEntered.png");
    
    //오른쪽 화살표 버튼
    private ImageIcon rightArrowIcon = new ImageIcon("rightArrow.png");
    private ImageIcon rightArrowEnteredIcon = new ImageIcon("rightArrowEntered.png");
    
    private ImageIcon[] rules = new ImageIcon[5]; //아래 이미지들을 이곳으로 옮기는것 고려
    
    private ImageIcon rule1 = new ImageIcon("rule1.png");
//    private ImageIcon rule2 = new ImageIcon("rule2.png");
//    private ImageIcon rule3 = new ImageIcon("rule3.png");
//    private ImageIcon rule4 = new ImageIcon("rule4.png");
//    private ImageIcon rule5 = new ImageIcon("rule5.png");
    
    //룰 이미지를 화면에 보여주기 위함
    private JLabel ruleLabel;
    
    private JLabel lastRuleLabel; //이전 라벨을 저장하도록
    private JLabel nextRuleLabel; //다음 번호의 라벨을 저장하도록
    
    private int index = 0;
    
    //이미지번호의 인덱스를 리턴
    public int getIndex() {
    	return index;
    }
    
    
    //다음 이미지로 넘어가게 하는 클래스 기존 이벤트 오버라이딩해서 작성
    private class NextImageButtonClickedEvent extends ButtonClickedEvent{

    	//생성자 호출
		public NextImageButtonClickedEvent(GameFrame parent, ImageIcon enteredIcon, ImageIcon presentIcon) {
			super(parent, enteredIcon, presentIcon);
			// TODO Auto-generated constructor stub
		}
		
		@Override //이벤트 무시
		public void mouseClicked(MouseEvent e) {
			
		}
		
		//마우스클릭 이벤트만 오버라이딩
		@Override
		public void mousePressed(MouseEvent e) {
			ruleLabel.setIcon(rules[index]);
			
			index++; //클릭하면 다음 인덱스로 넘어가도록
			if(index>=5) {
				index%=5; //모듈러연산 =>처음으로 돌아가게
			}
			
			JLabel label = (JLabel)(e.getComponent()); //이벤트가 발생한 라벨을 가져옴
//			label.setIcon(presentIcon); //원래 이미지로 변경
			//버튼이 눌려진 순간 소리가 나도록
			try {
				setClip(AudioSystem.getClip());
				File audioFile = new File("ButtonClick.wav");
				AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
				getClip().open(audioStream);
			}catch(Exception E) {
				System.out.println("오류!");
			}
			getClip().start(); // 버튼을 클릭했을때 소리가 나도록
			
		}
    	
    }
    

    
    public RulePanel(GameFrame parent) {
        this.parent = parent;//받은 부모를 전역변수로 저장한다
        setLayout(null); //배치 관리자 제거
        this.setBackground(Color.white);
        setSize(1500,900);
        
        for(int i=0;i<rules.length;i++) {
        	rules[i] = new ImageIcon("rule"+(i+1)+".png");
        }
        
        
        ruleLabel = new JLabel(rules[0]);
        //이미지들의 크기는 같음
        ruleLabel.setSize(rule1.getIconWidth(),rule1.getIconHeight());
        ruleLabel.setLocation(330,250);
        add(ruleLabel);
        
        
        //다음 이미지 버튼
        
        JLabel nextImageButton = new JLabel(rightArrowIcon);
        nextImageButton.setSize(rightArrowIcon.getIconWidth(),rightArrowIcon.getIconHeight());
        nextImageButton.setLocation(700,180);
        nextImageButton.addMouseListener(new  NextImageButtonClickedEvent(parent,rightArrowEnteredIcon,rightArrowIcon));
        add(nextImageButton);

        //뒤로가기 버튼 => 다시 4가지 메뉴 창으로 되돌아간다.
        //홈 버튼
  		JLabel homeButton = new JLabel(homeButtonIcon);
  		homeButton.setSize(homeButtonIcon.getIconWidth(),homeButtonIcon.getIconHeight());
  		homeButton.setLocation(1360,20);
  		homeButton.addMouseListener(new ButtonClickedEvent(parent,parent.BEGINNING_PANEL,homeButtonEnteredIcon,homeButtonIcon));
  		add(homeButton);
        
        setVisible(true);
    }
    
    
    @Override
    public void paintComponent(Graphics g) {
       super.paintComponent(g); //그래픽 컴포넌트 설정
       //배경 이미지
       g.drawImage(backgroundPanelImage, 0, 0, this.getWidth(),this.getHeight(),null); //이미지가 그려지는 시점 알림받지 않기
    }
  
}

