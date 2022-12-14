package game;

import java.awt.Graphics;

import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import game.GameFrame.Music;

public class BeginningPanel extends JPanel {
    private GameFrame parent;//부모 변수
    //배경 이미지
    private ImageIcon bgImageicon = new ImageIcon("background.png");
    private Image backgroundPanelImage = bgImageicon.getImage();
    
    
    //게임 시작 버튼(레이블) 이미지
    private ImageIcon selectLabelicon = new ImageIcon("goGame.png");
    private ImageIcon selectLabelEnteredicon = new ImageIcon("goGameEntered.png");
    //private Image gameStartLabelImage = selectLabelicon.getImage();
    //게임 설명 버튼(레이블) 이미지
    private ImageIcon ruleLabelIcon = new ImageIcon("goRule.png");
    private ImageIcon ruleLabelEnteredicon = new ImageIcon("goRuleEntered.png");
    
    //private Image ruleLabelImage = ruleLabelIcon.getImage();
    //랭킹 보기 버튼(레이블) 이미지
    private ImageIcon langkingLabelIcon = new ImageIcon("goRangking.png");
    private ImageIcon langkingLabelEnteredIcon = new ImageIcon("goRangkingEntered.png");
    
    //private Image showRangkingLabelImage = langkingLabelIcon.getImage();
    //단어 편집 버튼(레이블) 이미지
    private ImageIcon editLabelIcon = new ImageIcon("goEdit.png");
    private ImageIcon editLabelEnteredicon = new ImageIcon("goEditEntered.png");
    
    //private Image EditButtonImage = editLabelIcon.getImage();
    
    private ImageIcon soundLabelIcon = new ImageIcon("music.png");
    private ImageIcon soundLabelMuteIcon = new ImageIcon("mute.png");
    
    private Music music; //시작 패널에서 음악을 끌수있도록
    
    private boolean MusicOn = true;//현재 음악이 플레이중인지 여부를 리턴
    
    
    //현재 음악 재생상태를 리턴한다.
    public boolean isMusicOn() {
    	return MusicOn;
    }
//    TestFrame tf;
//    
    //현재 음악상태를 변경
    public void setMusicOn(boolean onOff) {
    	MusicOn = onOff;
    }
    
    
    //음악을 관리하기 위해 기존에 작성한 버튼클릭이벤트를 오버라이딩하여 새로운 기능 작성
    private class SoundButtonClickedEvent extends ButtonClickedEvent{
    	private GameFrame parent;
    	
    	public SoundButtonClickedEvent(GameFrame parent,ImageIcon enteredIcon, ImageIcon presentIcon) {
    		super(parent,enteredIcon, presentIcon); //부모 생성자에 넘겨준다.
    		// TODO Auto-generated constructor stub
    		this.parent = parent;
    	}


    	@Override //마우스가 올라갔을때의 이벤트를 무시하기 위해 오버라이딩
    	public void mouseEntered(MouseEvent e) {
			
    	}
    	
    	@Override //마우스 벗어날때 효과 무시 오버라이딩
    	public void mouseExited(MouseEvent e) {
    		
    	}
    	
    	@Override //마우스가 클릭되었을때 현재 음악이 작동중인지 확인하여 음악을 키고 끔
    	public void mouseClicked(MouseEvent e) {
    		JLabel label = (JLabel)(e.getComponent()); //이벤트가 발생한 라벨을 가져옴
    		if(isMusicOn()) {//현재 음악이 재생중이라면
    			System.out.println("음악 재생중 -> 중지");
    			label.setIcon(getEnteredIcon()); //x표시로 변경
    			parent.getMusic().musicStop();
    			setMusicOn(false); //음악 꺼진상태로 표시
    		}
    			
    		else {//현재 음악이 꺼져있는 상태라면
    			System.out.println("음악 정지 -> 재생");
    			label.setIcon(getPresentIcon()); //음표 표시로 변경
    			parent.getMusic().resumeMusic();
//    			parent.getMusic().start();//멈춘 이후부터 음악재생
    			setMusicOn(true); //음악 켜진상태로 표시
    		}
    	}
    }
    	
    
    
    
    
    public BeginningPanel(GameFrame parent) {
        this.parent = parent;//부모를 입력받아 변수에 저장
//        this.music = music;// 음악을 관리하도록 하기 위하여
        
        setLayout(null); //배치 관리자 제거
        
        //소리 조작 버튼
        JLabel soundButtonLabel = new JLabel(soundLabelIcon);
        soundButtonLabel.setSize(soundLabelIcon.getIconWidth(),soundLabelIcon.getIconHeight());
        soundButtonLabel.setLocation(20, 20);
        soundButtonLabel.addMouseListener(new SoundButtonClickedEvent(parent,soundLabelMuteIcon,soundLabelIcon));
        		
//        		new ButtonClickedEvent(parent, GameFrame.SELECT_PANEL,soundLabelMuteIcon,soundLabelIcon));
        add(soundButtonLabel);
        
        //4개의 버튼을 달고 있는 panel을 생성
        //1. 게임 시작 버튼
        //=> 버튼을 누르면 프레임의 패널을 선택 패널로 이동, 기존 패널은 프레임에서 제거
        JLabel startButtonLabel = new JLabel(selectLabelicon);
        startButtonLabel.setSize(selectLabelicon.getIconWidth(),selectLabelicon.getIconHeight());
        startButtonLabel.setLocation(398, 220);
        startButtonLabel.addMouseListener(new ButtonClickedEvent(parent, GameFrame.SELECT_PANEL,selectLabelEnteredicon,selectLabelicon));
        add(startButtonLabel);		
        
        //2. 규칙 설명 버튼
        //=> 버튼을 누르면 프레임의 패널을 규칙설명패널로 이동, 기존 패널은 프레임에서 제거
        JLabel ruleButtonLabel = new JLabel(ruleLabelIcon);
        ruleButtonLabel.setSize(ruleLabelIcon.getIconWidth(),ruleLabelIcon.getIconHeight());
        ruleButtonLabel.setLocation(398, 348);
        ruleButtonLabel.addMouseListener(new ButtonClickedEvent(parent, GameFrame.RULE_PANEL,ruleLabelEnteredicon,ruleLabelIcon));
        add(ruleButtonLabel);

        //3. 단어 편집 버튼
        //=> 버튼을 누르면 프레임의 패널을 단어편집패널로 이동, 기존 패널은 프레임에서 제거
        JLabel wordEditButtonLabel = new JLabel(editLabelIcon);
        wordEditButtonLabel.setSize(editLabelIcon.getIconWidth(),editLabelIcon.getIconHeight());
        wordEditButtonLabel.setLocation(398, 478);
        wordEditButtonLabel.addMouseListener(new ButtonClickedEvent(parent, GameFrame.EDIT_PANEL,editLabelEnteredicon,editLabelIcon));
        add(wordEditButtonLabel);

        //4. 순위 보기 버튼
        //=> 버튼을 누르면 프레임의 패널을 랭킹패널로 이동, 기존 패널은 프레임에서 제거
        JLabel showLankingButtonLabel = new JLabel(langkingLabelIcon);
        showLankingButtonLabel.setSize(langkingLabelIcon.getIconWidth(),langkingLabelIcon.getIconHeight());
        showLankingButtonLabel.setLocation(398, 613);
        showLankingButtonLabel.addMouseListener(new ButtonClickedEvent(parent, GameFrame.RANKING_PANEL,langkingLabelEnteredIcon,langkingLabelIcon));
        add(showLankingButtonLabel);

        
//        //새로운 프레임 생성용 테스트 버튼
//        JButton testButton = new JButton("프레임 생성");
//        testButton.setSize(500, 100);
//        testButton.setLocation(500, 100);
//        testButton.addMouseListener(new MouseAdapter() {
//        	@Override
//        	public void mouseClicked(MouseEvent e) {
//        		System.out.println("버튼 눌려짐");
//        		if(tf==null) {
//        			tf = new TestFrame();
//        			tf.setVisible(true);
//        			tf.setSize(250,100);
//        			tf.setLocation(200,200);
//        		}
//        		else {
//        			System.out.println("이미 생성되어있음");
//        		}
//        		//new TestFrame(); // 버튼을 누른 순간 새로운 프레임이 생성됨
//        	}
//        });
//        //add(testButton);
    }
    
    @Override
    public void paintComponent(Graphics g) {
       super.paintComponent(g); //그래픽 컴포넌트 설정
       //배경 이미지
       g.drawImage(backgroundPanelImage, 0, 0, bgImageicon.getIconWidth(),bgImageicon.getIconHeight(),null); //이미지가 그려지는 시점 알림받지 않기
    }
    
    
}