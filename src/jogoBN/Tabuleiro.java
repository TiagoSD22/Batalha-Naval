package jogoBN;

import java.util.Random;

public class Tabuleiro {
	Random rand;
	int matriz[][],ids[][];
	int PAHP,NGHP,CHP,SHP,DHP;
	boolean pah,ch,sh,dh,ngh;
	int pax,pay,cx,cy,sx,sy,ngx,ngy,dx,dy;
	
	public Tabuleiro(){
		rand = new Random();
		matriz = new int[10][10];
		ids = new int[10][10];
		IniciarTabuleiro();
		/*System.out.println("Matriz: ");
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				System.out.printf("%d ",matriz[i][j]);
			}
			System.out.println();
		}*/
	}
	
	public void IniciarTabuleiro(){
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				matriz[i][j] = 0;
				ids[i][j] = 0;
			}
		}
		ColocarBarcos();
	}
	
	public void ColocarBarcos(){
		pah = false;ch = false;ngh = false;sh = false;dh = false;
		ColocarPortaAvioes();
		ColocarNaviodeGuerra();
		ColocarCruzador();
		ColocarSubmarino();
		ColocarDestroyer();
		PAHP = 5;NGHP = 4;SHP = 3;CHP = 3;DHP = 2;
	}
	
	public void ColocarPortaAvioes(){
		int direcao = rand.nextInt(2);
		int i,j;
		if(direcao == 0){
			i = rand.nextInt(10);
			j = rand.nextInt(6);
			if(matriz[i][j] == 0){
				if(matriz[i][j+1] == 0 && matriz[i][j+2] == 0 && matriz[i][j+3] == 0 && matriz[i][j+4] == 0){
						matriz[i][j] = 2;
						matriz[i][j+1] = 4;
						matriz[i][j+2] = 4;
						matriz[i][j+3] = 4;
						matriz[i][j+4] = 3;
						ids[i][j] = 5;
						ids[i][j+1] = 5;
						ids[i][j+2] = 5;
						ids[i][j+3] = 5;
						ids[i][j+4] = 5;
						pah = true;pax = 20 + j*30;pay = 200 + i*30;
				}
				else{
					ColocarPortaAvioes();
				}
			}
			else{
				ColocarPortaAvioes();
			}
		}
		else{
			i = rand.nextInt(6);
			j = rand.nextInt(10);
			if(matriz[i][j] == 0){
				if(matriz[i+1][j] == 0 && matriz[i+2][j] == 0 && matriz[i+3][j] == 0 && matriz[i+4][j] == 0){
						matriz[i][j] = 6;
						matriz[i+1][j] = 4;
						matriz[i+2][j] = 4;
						matriz[i+3][j] = 4;
						matriz[i+4][j] = 5;
						ids[i][j] = 5;
						ids[i+1][j] = 5;
						ids[i+2][j] = 5;
						ids[i+3][j] = 5;
						ids[i+4][j] = 5;
						pax = 20 + j*30;pay = 200 + i*30;
						
				}
				else{
					ColocarPortaAvioes();
				}
			}
			else{
				ColocarPortaAvioes();
			}
		}
	}
	
	public void ColocarNaviodeGuerra(){
		int direcao = rand.nextInt(2);
		int i,j;
		if(direcao == 0){
			i = rand.nextInt(10);
			j = rand.nextInt(7);
			if(matriz[i][j] == 0){
				if(matriz[i][j+1] == 0 && matriz[i][j+2] == 0 && matriz[i][j+3] == 0){
						matriz[i][j] = 2;
						matriz[i][j+1] = 4;
						matriz[i][j+2] = 4;
						matriz[i][j+3] = 3;
						ids[i][j] = 4;
						ids[i][j+1] = 4;
						ids[i][j+2] = 4;
						ids[i][j+3] = 4;
						ngh = true;ngx = 20 + j*30;ngy = 200 + i*30;
				}
				else{
					ColocarNaviodeGuerra();
				}
			}
			else{
				ColocarNaviodeGuerra();
			}
		}
		else{
			i = rand.nextInt(7);
			j = rand.nextInt(10);
			if(matriz[i][j] == 0){
				if(matriz[i+1][j] == 0 && matriz[i+2][j] == 0 && matriz[i+3][j] == 0){
						matriz[i][j] = 6;
						matriz[i+1][j] = 4;
						matriz[i+2][j] = 4;
						matriz[i+3][j] = 5;
						ids[i][j] = 4;
						ids[i+1][j] = 4;
						ids[i+2][j] = 4;
						ids[i+3][j] = 4;
						ngx = 20 + j*30;ngy = 200 + i*30;
				}
				else{
					ColocarNaviodeGuerra();
				}
			}
			else{
				ColocarNaviodeGuerra();
			}
		}
	}
	
	public void ColocarCruzador(){
		int direcao = rand.nextInt(2);
		int i,j;
		if(direcao == 0){
			i = rand.nextInt(10);
			j = rand.nextInt(8);
			if(matriz[i][j] == 0){
				if(matriz[i][j+1] == 0 && matriz[i][j+2] == 0){
						matriz[i][j] = 2;
						matriz[i][j+1] = 4;
						matriz[i][j+2] = 3;
						ids[i][j] = 3;
						ids[i][j+1] = 3;
						ids[i][j+2] = 3;
						ch = true;
						cx = 20 + j*30;cy = 200 + i*30;
				}
				else{
					ColocarCruzador();
				}
			}
			else{
				ColocarCruzador();
			}
		}
		else{
			i = rand.nextInt(8);
			j = rand.nextInt(10);
			if(matriz[i][j] == 0){
				if(matriz[i+1][j] == 0 && matriz[i+2][j] == 0){
						matriz[i][j] = 6;
						matriz[i+1][j] = 4;
						matriz[i+2][j] = 5;
						ids[i][j] = 3;
						ids[i+1][j] = 3;
						ids[i+2][j] = 3;
						cx = 20 + j*30;cy = 200 + i*30;
				}
				else{
					ColocarCruzador();
				}
			}
			else{
				ColocarCruzador();
			}
		}
	}
	
	public void ColocarSubmarino(){
		int direcao = rand.nextInt(2);
		int i,j;
		if(direcao == 0){
			i = rand.nextInt(10);
			j = rand.nextInt(8);
			if(matriz[i][j] == 0){
				if(matriz[i][j+1] == 0 && matriz[i][j+2] == 0){
						matriz[i][j] = 2;
						matriz[i][j+1] = 4;
						matriz[i][j+2] = 3;
						ids[i][j] = 2;
						ids[i][j+1] = 2;
						ids[i][j+2] = 2;
						sh = true;
						sx = 20 + j*30;sy = 200 + i*30;
				}
				else{
					ColocarSubmarino();
				}
			}
			else{
				ColocarSubmarino();
			}
		}
		else{
			i = rand.nextInt(8);
			j = rand.nextInt(10);
			if(matriz[i][j] == 0){
				if(matriz[i+1][j] == 0 && matriz[i+2][j] == 0){
						matriz[i][j] = 6;
						matriz[i+1][j] = 4;
						matriz[i+2][j] = 5;
						ids[i][j] = 2;
						ids[i+1][j] = 2;
						ids[i+2][j] = 2;
						sx = 20 + j*30;sy = 200 + i*30;
				}
				else{
					ColocarSubmarino();
				}
			}
			else{
				ColocarSubmarino();
			}
		}
	}
	
	public void ColocarDestroyer(){
		int direcao = rand.nextInt(2);
		int i,j;
		if(direcao == 0){
			i = rand.nextInt(10);
			j = rand.nextInt(9);
			if(matriz[i][j] == 0){
				if(matriz[i][j+1] == 0){
						matriz[i][j] = 2;
						matriz[i][j+1] = 3;
						ids[i][j] = 1;
						ids[i][j+1] = 1;
						dh = true;
						dx = 20 + j*30;dy = 200 + i*30;
				}
				else{
					ColocarDestroyer();
				}
			}
			else{
				ColocarDestroyer();
			}
		}
		else{
			i = rand.nextInt(9);
			j = rand.nextInt(10);
			if(matriz[i][j] == 0){
				if(matriz[i+1][j] == 0){
						matriz[i][j] = 6;
						matriz[i+1][j] = 5;
						ids[i][j] = 1;
						ids[i+1][j] = 1;
						dx = 20 + j*30;dy = 200 + i*30;
				}
				else{
					ColocarDestroyer();
				}
			}
			else{
				ColocarDestroyer();
			}
		}
	}
	
	public void AtualizarHPs(int i, int j){
		if(ids[i][j] == 5){
			PAHP--;
		}
		if(ids[i][j] == 4){
			NGHP--;
		}
		if(ids[i][j] == 3){
			CHP--;
		}
		if(ids[i][j] == 2){
			SHP--;
		}
		if(ids[i][j] == 1){
			DHP--;
		}
	}
	
	public boolean Destruiu(int i, int j){
		if(ids[i][j] == 5){
			if(PAHP == 0){
				return true;
			}
		}
		if(ids[i][j] == 4){
			if(NGHP == 0){
				return true;
			}
		}
		if(ids[i][j] == 3){
			if(CHP == 0){
				return true;
			}
		}
		if(ids[i][j] == 2){
			if(SHP == 0){
				return true;
			}
		}
		if(ids[i][j] == 1){
			if(DHP == 0){
				return true;
			}
		}
		return false;
	}
	
	public int RetornarHP(int i, int j){
		int hp = 0;
		if(ids[i][j] == 1){
			hp = DHP;
		}
		if(ids[i][j] == 2){
			hp = SHP;
		}
		if(ids[i][j] == 3){
			hp = CHP;
		}
		if(ids[i][j] == 4){
			hp = NGHP;
		}
		if(ids[i][j] == 5){
			hp = PAHP;
		}
		return hp;
	}
}
