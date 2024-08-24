import java.util.*;
class Abhimanyu {

	final static int circle = 11;
	static int maxPower;
	static Boolean dp[][][][][][];

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int enemyPowers[] = new int[circle];

		for(int i = 0;i < circle;i++) {
			enemyPowers[i] = sc.nextInt();
		}
		int initalPower = sc.nextInt();

		maxPower = initalPower;
		
		// max skip and recharge can't be more than 11 and also there is no benefit for 
		// Abhimanyu to jump to some previous circle
		int a = sc.nextInt() , b = sc.nextInt();

		int skipCount = Math.min(11,a); 
		int rechargeCount = Math.min(11,b);

		dp = new Boolean[circle][maxPower + 1][skipCount + 1][rechargeCount + 1][2][2];

		boolean canCrossChakravyuha = solve(0,initalPower,enemyPowers,skipCount , rechargeCount,0,0);
		System.out.println(canCrossChakravyuha?"Yes Abhimanyu can cross the Chakravyuha":"No can not pass through Chakravyuha");
	}
	public static boolean solve(int currentCircle ,int remaningPower,int enemyPowers[], int skipCount,int rechargeCount,int k3,int k7) {
		if(currentCircle >= 11) return true;
		// if(remaningPower < 0) return false;
		if(dp[currentCircle][remaningPower][skipCount][rechargeCount][k3][k7] != null) {
			return dp[currentCircle][remaningPower][skipCount][rechargeCount][k3][k7];	
		}
		boolean canCrossChakravyuha = false;
		// skiping current circle
		if(remaningPower >= 0 && skipCount > 0) {
			canCrossChakravyuha |= solve(currentCircle + 1 , remaningPower , enemyPowers, skipCount-1 ,rechargeCount,k3,k7);
		}
		// decides to recharge himself
		if(rechargeCount > 0) {
			canCrossChakravyuha |= solve(currentCircle ,maxPower,enemyPowers,skipCount,rechargeCount - 1,k3,k7);
		}
		// decides to not recharge
		// checking for backStab from k3 and k7
		int leftPower = remaningPower;
		if(currentCircle == 3 || currentCircle == 7) {
			int powerLoss = 0;
			if(currentCircle == 3) {
				powerLoss = k3 == 1?enemyPowers[currentCircle-1]/2:enemyPowers[currentCircle-1];
			}
			else {
				powerLoss = k7 == 1?enemyPowers[currentCircle-1]/2:enemyPowers[currentCircle-1];	
			}
			leftPower -= powerLoss;
		}
		// fightinh with enemy of current circle
		if(leftPower >= enemyPowers[currentCircle]) {
			int nk3 = currentCircle == 2?1:k3;
			int nk7 = currentCircle == 6?1:k7;
			canCrossChakravyuha |= solve(currentCircle+1,leftPower - enemyPowers[currentCircle], enemyPowers , skipCount,rechargeCount,nk3,nk7); // fightiing in current circle	
		}
		return dp[currentCircle][remaningPower][skipCount][rechargeCount][k3][k7] = canCrossChakravyuha;
	}
}


// 3 15 4 6 0 17 33 20 8 2 1
// 40
// 5 
// 1


