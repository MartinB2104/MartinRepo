package cryptoTrading;

public class Trade {
	private String coinName;
	//price where the trade started
	private double buyPrice;
	//with how much money you are in the trade
	private double marge;
	//profit and loss, amount you won or lost
	private double pAndL;
	//return on investment in %
	private double rOE;
	private boolean isWin = false;
	private boolean isLong = true;
	
	public  Trade() {}
	
	public Trade(String coinName) {
		this.coinName = coinName;
	}
	
	public Trade(String coinName, double buyPrice) {
		this.coinName = coinName;
		this.buyPrice = buyPrice;
	}
	
	public Trade(String coinName, double buyPrice, double marge) {
		this.coinName = coinName;
		this.buyPrice = buyPrice;
		this.marge = marge;
	}
	
	public String getCoinName() {
		return coinName;
	}
	public void setCoinName(String coinName) {
		this.coinName = coinName;
	}
	public double getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}
	public double getMarge() {
		return marge;
	}
	public void setMarge(double marge) {
		this.marge = marge;
	}
	public boolean getIsLong() {
		return isLong;
	}

	public void setIsLong(boolean isLong) {
		this.isLong = isLong;
	}

	public double getPAndL() {
		return pAndL;
	}
	public void setpAndL(double pAndL) {
		this.pAndL = pAndL;
	}
	public double getROE() {
		return rOE;
	}
	public void setROE(double rOE) {
		this.rOE = rOE;
	}

	public boolean getWin() {
		return isWin;
	}

	public void setWin(boolean isWin) {
		this.isWin = isWin;
	}

	@Override
	public String toString() {
		return "Trade{" +
				"coinName='" + coinName + '\'' +
				", buyPrice=" + buyPrice +
				", marge=" + marge +
				", pAndL=" + pAndL +
				", rOE=" + rOE +
				", isWin=" + isWin +
				", isLong=" + isLong +
				'}';
	}
}
