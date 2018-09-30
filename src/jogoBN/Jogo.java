package jogoBN;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

@SuppressWarnings("serial")
public class Jogo extends JComponent implements ActionListener{
	
	ImageIcon background,missile,titulo,seucampo,campooponente,agua,aguaatingida,
	explosao,morto,vida,vidavazia,destroyerH,destroyerV,submarinoH,submarinoV,cruzadorH,cruzadorV,
	portaavioesV,portaavioesH,naviodeguerraV,naviodeguerraH,painel,barra,recomecar;
	int xm = 0,ym = 0,limitex,iatual,jatual,ultimoi,ultimoj;
	boolean atirou = false,casasjogadas[][],jogadaspc[][],vezdojogador = true,segundajogada = false,segundajogadaPC = false;
	static JButton celulas[][],novo;
	static JLabel info;
	Timer timer;
	Tabuleiro tab,tabpc;
	Sons som;
	Random rand = new Random();
	JFrame fim;
	static Jogo jogo;
	static JFrame window;
	
	public static void main(String[] args){
		window =new JFrame("Batalha Naval");
		jogo = new Jogo();
		jogo.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/cursor.gif")).getImage(),new Point(0,0),"cursor"));
		window.add(jogo);
		for(int i = 0; i < 10; i++){
			for(int j = 0; j <10; j++){
				jogo.add(celulas[i][j]);
			}
		}
		jogo.add(info);
		jogo.add(novo);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
	
	public void Novo(){
		window.dispose();
		main(null);
	}

	public Jogo(){
		CarregarImagens();
		novo = new JButton();
		novo.setOpaque(false);
		novo.setBackground(Color.gray);
		novo.setBorderPainted(false);
		novo.setToolTipText("RECOMEÇAR");
		novo.setBounds(520,101,69,71);
		novo.setIcon(recomecar);
		novo.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				Novo();
			}
		});
		som = new Sons();
		tab = new Tabuleiro();
		tabpc = new Tabuleiro();
		timer = new Timer(1,this);
		celulas = new JButton[10][10];
		casasjogadas = new boolean[10][10];
		jogadaspc = new boolean[10][10];
		info = new JLabel();
		info.setHorizontalAlignment(SwingConstants.CENTER);
		info.setBounds(335,565,400,20);
		info.setVisible(false);
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				celulas[i][j] = new JButton();
				celulas[i][j].setBounds((750 + j*30),(200 + i*30),30,30);
				celulas[i][j].addActionListener(this);
				casasjogadas[i][j] = false;
				jogadaspc[i][j] = false;
			}
		}
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				celulas[i][j].addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e){
						int x = 0,y = 0;
						while(!e.getSource().equals(celulas[x][y])){
							y++;
							if(y == 10){
								y = 0;
								x++;
							}
						}
						if(!timer.isRunning()){
						if(vezdojogador){
							if(!casasjogadas[x][y]){
								som.Tocar("tiro");
								info.setVisible(false);
								casasjogadas[x][y] = true;
								xm = 340;
								ym = celulas[x][y].getY();
								limitex = celulas[x][y].getX();
								timer.start();
								atirou = true;
								iatual = x;
								jatual = y;
							}
							else{
								info.setVisible(true);
								info.setText("VOCÊ JÁ ATINGIU ESSA CASA!");
							}
						}
						}
					}
				});
			}
		}
		DesenharTabuleiroPc();
	}
	
	public void JogarVizinhanca(int i, int j){
		if(((jogadaspc[i-1][j] && tab.matriz[i-1][j] == 0) && (jogadaspc[i+1][j] && tab.matriz[i+1][j] == 0)) ||
		(jogadaspc[i][j-1] && tab.matriz[i][j-1] != 0 && tab.RetornarHP(i, j-1) == tab.RetornarHP(i, j)) ||
		(jogadaspc[i][j+1] && tab.matriz[i][j+1] != 0 && tab.RetornarHP(i, j+1) == tab.RetornarHP(i, j))){
			System.out.println("Eu jogaria na horizontal!");
		}
	}
	
	public void RealizarJogadaPC(){
		if(tab.RetornarHP(ultimoi,ultimoj) > 0){
			JogarVizinhanca(ultimoi,ultimoj);
		}
		int i,j;
		i = rand.nextInt(10);
		j = rand.nextInt(10);
		if(jogadaspc[i][j]){
			do{
				i = rand.nextInt(10);
				j = rand.nextInt(10);
			}while(jogadaspc[i][j]);
		}
		xm = 720;
		ym = 190 + i*30;
		limitex = (20 + j*30) + 15;
		iatual = i;jatual = j;
		som.Tocar("tiro");
		timer.start();
	}
	
	public void CarregarImagens(){
		titulo = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/titulo.png"));
		seucampo = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/seucampo.png"));
		campooponente = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/campoinimigo.png"));
		background = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/bn.jpg"));
		missile = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/tiro.png"));
		agua = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/agua.jpg"));
		aguaatingida = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/aguaatingida.jpg"));
		explosao = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/explosao.jpg"));
		vida = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/vida.png"));
		vidavazia = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/vidavazia.png"));
		destroyerH = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/DestroyerH.png"));
		destroyerV = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/DestroyerV.png"));
		submarinoV = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/SubmarinoV.png"));
		submarinoH = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/SubmarinoH.png"));
		cruzadorV = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/CruzadorV.png"));
		cruzadorH = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/CruzadorH.png"));
		naviodeguerraV = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/NaviodeGuerraV.png"));
		naviodeguerraH = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/NaviodeGuerraH.png"));
		portaavioesV = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/PortaAvioesV.png"));
		portaavioesH = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/PortaAvioesH.png"));
		painel = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/painel.gif"));
		morto = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/morto.jpg"));
		barra = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/barra.gif"));
		recomecar = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/novo.png"));
	}
	
	public void DesenharBlocoPc(int i, int j, JButton bloco){
		if(!casasjogadas[i][j]){
			bloco.setIcon(agua);
		}
		else{
			if(tabpc.matriz[i][j] == 0){
				bloco.setIcon(aguaatingida);
			}
			else{
				bloco.setIcon(explosao);
			}
		}
	}
	
	public void DesenharTabuleiroPc(){
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				DesenharBlocoPc(i,j,celulas[i][j]);
			}
		}
	}
	
	@Override
	public Dimension getPreferredSize(){
		return new Dimension(1080,700);
	}
	
	@Override
	public void paintComponent(Graphics g){
		int i,j;
		super.paintComponent(g);
		g.drawImage(background.getImage(), 0, 0, null);
		
		for(i = 0; i < 10; i++){
			for(j = 0; j < 10; j++){
				g.drawImage(agua.getImage(),20 + j*30,200 + i*30,null);
			}
		}
		
		if(tab.dh){
			g.drawImage(destroyerH.getImage(),tab.dx,tab.dy,null);
		}
		else{
			g.drawImage(destroyerV.getImage(),tab.dx,tab.dy,null);
		}
		if(tab.sh){
			g.drawImage(submarinoH.getImage(),tab.sx,tab.sy,null);
		}
		else{
			g.drawImage(submarinoV.getImage(),tab.sx,tab.sy,null);
		}
		if(tab.ch){
			g.drawImage(cruzadorH.getImage(), tab.cx, tab.cy,null);
		}
		else{
			g.drawImage(cruzadorV.getImage(), tab.cx, tab.cy,null);
		}
		if(tab.ngh){
			g.drawImage(naviodeguerraH.getImage(), tab.ngx, tab.ngy,null);
		}
		else{
			g.drawImage(naviodeguerraV.getImage(), tab.ngx, tab.ngy,null);
		}
		if(tab.pah){
			g.drawImage(portaavioesH.getImage(), tab.pax,tab.pay,null);
		}
		else{
			g.drawImage(portaavioesV.getImage(), tab.pax,tab.pay,null);
		}
		
		g.drawImage(painel.getImage(),20, 515,null);
		for(i = 0; i < tab.PAHP; i++){
			g.drawImage(vida.getImage(),145 + 30*i,530,null);
		}
		if(tab.PAHP < 5){
			for(j = tab.PAHP; j < 5; j++){
				g.drawImage(vidavazia.getImage(),145 + 30*j,530,null);
			}
		}
		for(i = 0; i < tab.NGHP; i++){
			g.drawImage(vida.getImage(),145 + 30*i,560,null);
		}
		if(tab.NGHP < 4){
			for(j = tab.NGHP; j < 4; j++){
				g.drawImage(vidavazia.getImage(),145 + 30*j,560,null);
			}
		}
		for(i = 0; i < tab.CHP; i++){
			g.drawImage(vida.getImage(),145 + 30*i,590,null);
		}
		if(tab.CHP < 3){
			for(j = tab.CHP; j < 3; j++){
				g.drawImage(vidavazia.getImage(),145 + 30*j,590,null);
			}
		}
		for(i = 0; i < tab.SHP; i++){
			g.drawImage(vida.getImage(),145 + 30*i,620,null);
		}
		if(tab.SHP < 3){
			for(j = tab.SHP; j < 3; j++){
				g.drawImage(vidavazia.getImage(),145 + 30*j,620,null);
			}
		}
		for(i = 0; i < tab.DHP; i++){
			g.drawImage(vida.getImage(),145 + 30*i,650,null);
		}
		if(tab.DHP < 2){
			for(j = tab.DHP; j < 2; j++){
				g.drawImage(vidavazia.getImage(),145 + 30*j,650,null);
			}
		}
		
		g.drawImage(painel.getImage(),750, 515,null);
		for(i = 0; i < tabpc.PAHP; i++){
			g.drawImage(vida.getImage(),875 + 30*i,530,null);
		}
		if(tabpc.PAHP < 5){
			for(j = tabpc.PAHP; j < 5; j++){
				g.drawImage(vidavazia.getImage(),875 + 30*j,530,null);
			}
		}
		for(i = 0; i < tabpc.NGHP; i++){
			g.drawImage(vida.getImage(),875 + 30*i,560,null);
		}
		if(tabpc.NGHP < 4){
			for(j = tabpc.NGHP; j < 4; j++){
				g.drawImage(vidavazia.getImage(),875 + 30*j,560,null);
			}
		}
		for(i = 0; i < tabpc.CHP; i++){
			g.drawImage(vida.getImage(),875 + 30*i,590,null);
		}
		if(tabpc.CHP < 3){
			for(j = tabpc.CHP; j < 3; j++){
				g.drawImage(vidavazia.getImage(),875 + 30*j,590,null);
			}
		}
		for(i = 0; i < tabpc.SHP; i++){
			g.drawImage(vida.getImage(),875 + 30*i,620,null);
		}
		if(tabpc.SHP < 3){
			for(j = tabpc.SHP; j < 3; j++){
				g.drawImage(vidavazia.getImage(),875 + 30*j,620,null);
			}
		}
		for(i = 0; i < tabpc.DHP; i++){
			g.drawImage(vida.getImage(),875 + 30*i,650,null);
		}
		if(tabpc.DHP < 2){
			for(j = tabpc.DHP; j < 2; j++){
				g.drawImage(vidavazia.getImage(),875 + 30*j,650,null);
			}
		}
		g.drawImage(titulo.getImage(),275,30,null);
		g.drawImage(seucampo.getImage(),8,135,null);
		g.drawImage(campooponente.getImage(),735, 135,null);
		if(vezdojogador){
			if(xm < limitex - 50){
				g.drawImage(missile.getImage(), xm, ym,null);
			}
		}
		else{
			if(xm > limitex){
				g.drawImage(missile.getImage(), xm, ym,null);
			}
		}
		for(i = 0; i < 10; i++){
			for(j = 0; j < 10; j++){
				if(jogadaspc[i][j]){
					if(tab.matriz[i][j] == 0){
						g.drawImage(aguaatingida.getImage(),20 + j*30,200 + i*30,null);
					}
					else{
						g.drawImage(explosao.getImage(),20 + j*30,200 + i*30,null);
					}
				}
			}
		}
		if(info.isVisible()){
			g.drawImage(barra.getImage(),335,550,null);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(vezdojogador){
			if(xm < limitex){
				xm+=3;
				if(xm < (limitex + 340)/2){
					ym--;
				}
				else{
					ym++;
				}
				repaint();
			}
			else{
				DesenharTabuleiroPc();
				if(timer.isRunning()){
					ProcessarJogada(tabpc,iatual,jatual);
				}
			}
		}
		else{
			if(xm > limitex){
				xm-=3;
				if(xm > (limitex + 740)/2){
					ym--;
				}
				else{
					ym++;
				}
				repaint();
			}
			else{
				if(timer.isRunning()){
					ProcessarJogadaPC(iatual,jatual);
				}
			}
		}
	}
	
	public void ProcessarJogada(Tabuleiro tab, int i, int j){
		info.setVisible(true);
		info.setFont(new Font("Purisa",Font.BOLD,12));
		if(tab.matriz[i][j] == 0){
			info.setText("VOCÊ ACERTOU NA ÁGUA!");
			som.Tocar("agua");
			timer.stop();
			segundajogada = false;
			vezdojogador = false;
			RealizarJogadaPC();
		}
		else{
			tab.AtualizarHPs(i,j);
			repaint();
			info.setVisible(true);
			if(segundajogada){
				timer.stop();
				vezdojogador = false;
				segundajogada = false;
				RealizarJogadaPC();
			}
			else{
				segundajogada = true;
				timer.stop();
			}
			if(!tab.Destruiu(i, j)){
				som.Tocar("acertou");
				info.setText("VOCÊ ACERTOU PARTE DE UMA EMBARCAÇÃO!");
			}
			else{
				som.Tocar("explodiu");
				if(tab.ids[i][j] == 5){
					info.setText("VOCÊ DESTRUIU O PORTA AVIÕES DO COMPUTADOR!");
				}
				if(tab.ids[i][j] == 4){
					info.setFont(new Font("Purisa",Font.BOLD,10));
					info.setText("VOCÊ DESTRUIU O NAVIO DE GUERRA DO COMPUTADOR!");
				}
				if(tab.ids[i][j] == 3){
					info.setText("VOCÊ DESTRUIU O CRUZADOR DO COMPUTADOR!");
				}
				if(tab.ids[i][j] == 2){
					info.setText("VOCÊ DESTRUIU O SUBMARINO DO COMPUTADOR!");
				}
				if(tab.ids[i][j] == 1){
					info.setText("VOCÊ DESTRUIU O DESTROYER DO COMPUTADOR!");
				}
				GameOver();
			}
		}
	}
	
	public void ProcessarJogadaPC(int i, int j){
		jogadaspc[i][j] = true;
		info.setVisible(true);
		info.setFont(new Font("Purisa",Font.BOLD,12));
		repaint();
		if(tab.matriz[i][j] == 0){
			info.setText("O COMPUTADOR ACERTOU NA ÁGUA!");
			som.Tocar("agua");
			timer.stop();
			vezdojogador = true;
			segundajogadaPC = false;
		}
		else{
			tab.AtualizarHPs(i,j);
			repaint();
			ultimoi = i;
			ultimoj = j;
			if(!tab.Destruiu(i, j)){
				som.Tocar("acertou");
				info.setText("O COMPUTADOR ACERTOU UMA EMBARCAÇÃO SUA!");
			}
			else{
				som.Tocar("explodiu");
				if(tab.ids[i][j] == 5){
					info.setText("O COMPUTADOR DESTRUIU SEU PORTA AVIÕES!");
				}
				if(tab.ids[i][j] == 4){
					info.setFont(new Font("Purisa",Font.BOLD,10));
					info.setText("O COMPUTADOR DESTRUIU SEU NAVIO DE GUERRA!");
				}
				if(tab.ids[i][j] == 3){
					info.setText("O COMPUTADOR DESTRUIU SEU CRUZADOR!");
				}
				if(tab.ids[i][j] == 2){
					info.setText("O COMPUTADOR DESTRUIU SEU SUBMARINO!");
				}
				if(tab.ids[i][j] == 1){
					info.setText("O COMPUTADOR DESTRUIU SEU DESTROYER!");
				}
				GameOver();
			}
			if(!segundajogadaPC){
				info.setVisible(true);
				RealizarJogadaPC();
				segundajogadaPC = true;
			}
			else{
				timer.stop();
				segundajogadaPC = false;
				vezdojogador = true;
			}
		}
	}
	
	public void GameOver(){
		if((tabpc.PAHP == 0 && tabpc.NGHP == 0 && tabpc.CHP == 0 && tabpc.SHP == 0 && tabpc.DHP == 0) ||
		(tab.PAHP == 0 && tab.NGHP == 0 && tab.CHP == 0 && tab.SHP == 0 && tab.DHP == 0)){
			fim = new JFrame();
			fim.setBounds(100, 100,400,200);
			fim.setLayout(null);
			fim.setVisible(true);
			fim.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			JLabel label = new JLabel();
			label.setBounds(((fim.getWidth() - 1)/2) - 165, 10, 330, 20);
			label.setHorizontalAlignment(SwingConstants.CENTER);
			fim.getContentPane().add(label);
			JButton novo = new JButton("Novo Jogo");
			novo.setBounds(((fim.getWidth() - 1)/2) - 90, 90, 180, 20);
			novo.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent e){
					fim.dispose();
					Novo();
				}
			});
			fim.getContentPane().add(novo);
			if(tabpc.PAHP == 0 && tabpc.NGHP == 0 && tabpc.CHP == 0 && tabpc.SHP == 0 && tabpc.DHP == 0){
				label.setText("VOCÊ VENCEU!");
			}
			else{
				label.setText("VOCÊ PERDEU!");
			}
		}
	}
}
