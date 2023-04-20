
public class Interval {
	private int days;
	private int hours;
	private int minutes;
	private boolean sign;

	public Interval(int days) {
		this.days = days;
		hours = 0;
		minutes = 0;
		sign = true;
	}

	public Interval(int days, int hours, int minutes, boolean sign) {
		this.days = days;
		this.hours = hours;
		this.minutes = minutes;
		this.sign = sign;
	}

	public int getDays() {
		return this.days;
	}

	public int getHours() {
		return this.hours;
	}

	public int getMinutes() {
		return this.minutes;
	}

	public boolean getSign() {
		return this.sign;
	}

	public Interval add(Interval another) {
		int noviDani;
		int noviSati;
		int noviMinuti;
		boolean noviSign;
		if (this.sign != another.getSign()) {
			if (this.veci(another)) {
				return sabiranje(this, another);
			} else {
				return sabiranje(another, this);
			}
		} else {
			noviDani = this.days + another.getDays();
			noviSati = this.hours + another.getHours();
			noviMinuti = this.minutes + another.getMinutes();
			if (noviMinuti >= 60) {
				noviMinuti = noviMinuti - 60;
				noviSati++;
			}
			if (noviSati >= 24) {
				noviSati = noviSati - 24;
				noviDani++;
			}
			noviSign = this.sign;
			Interval novi = new Interval(noviDani, noviSati, noviMinuti, noviSign);
			return novi;
		}
	}

	public Interval subtract(Interval another) {
		if (another.getSign() == false) {
			Interval gg = new Interval(another.getDays(), another.getHours(), another.getMinutes(), true);
			return add(gg);
		} else {
			Interval gg = new Interval(another.getDays(), another.getHours(), another.getMinutes(), false);
			return add(gg);
		}

	}

	public boolean equals(Interval another) {
		if ((this.getMinutes() == 0 && another.getMinutes() == 0) && (this.getHours() == 0 && another.getHours() == 0)
				&& (this.getDays() == 0 && another.getDays() == 0)) {
			return true;
		} else if (this.kojDanVeci(another) == 3 && this.kojSatVeci(another) == 3 && this.kojMinutVeci(another) == 3
				&& this.sign == another.getSign()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean lessThan(Interval another) {
		if (this.getSign() == true && another.getSign() == false) {
			return false;
		} else if (this.getSign() == false && another.getSign() == true) {
			return true;
		} else if (this.getSign() == true && another.getSign() == true) {
			if (this.getDays() < another.getDays()) {
				return true;
			} else if (this.getDays() > another.getDays()) {
				return false;
			} else {
				if (this.getHours() < another.getHours()) {
					return true;
				} else if (this.getHours() > another.getHours()) {
					return false;
				} else {
					if (this.getMinutes() < another.getMinutes()) {
						return true;
					} else if (this.getMinutes() > another.getMinutes()) {
						return false;
					} else {
						return false;
					}
				}
			}
		} else {
			if (this.getDays() > another.getDays()) {
				return true;
			} else if (this.getDays() < another.getDays()) {
				return false;
			} else {
				if (this.getHours() > another.getHours()) {
					return true;
				} else if (this.getHours() < another.getHours()) {
					return false;
				}
				if (this.getMinutes() > another.getMinutes()) {
					return true;
				} else if (this.getMinutes() < another.getMinutes()) {
					return false;
				} else {
					return false;
				}
			}
		}
	}

	public boolean veci(Interval another) {
		if (this.getDays() > another.getDays()) {
			return true;
		} else if (this.getDays() < another.getDays()) {
			return false;
		} else {
			if (this.getHours() > another.getHours()) {
				return true;
			} else if (this.getHours() < another.getHours()) {
				return false;
			} else {
				if (this.getMinutes() > another.getMinutes()) {
					return true;
				} else if (this.getMinutes() < another.getMinutes()) {
					return false;
				} else {
					return false;
				}
			}
		}
	}

	public boolean greaterThan(Interval another) {
		if (this.getSign() == true && another.getSign() == false) {
			return true;
		} else if (this.getSign() == false && another.getSign() == true) {
			return false;
		} else if (this.getSign() == false && another.getSign() == false) {
			if (another.getDays() < this.getDays()) {
				return false;
			} else if (another.getDays() > this.getDays()) {
				return true;
			} else {
				if (another.getHours() < this.getHours()) {
					return false;
				} else if (another.getHours() > this.getHours()) {
					return true;
				} else {
					if (another.getMinutes() < this.getMinutes()) {
						return false;
					} else if (another.getMinutes() > this.getMinutes()) {
						return true;
					} else {
						return false;
					}
				}
			}
		} else {
			if (this.getDays() > another.getDays()) {
				return true;
			} else if (this.getDays() < another.getDays()) {
				return false;
			} else {
				if (this.getHours() > another.getHours()) {
					return true;
				} else if (this.getHours() < another.getHours()) {
					return false;
				} else {
					if (this.getMinutes() > another.getMinutes()) {
						return true;
					} else if (this.getDays() < another.getDays()) {
						return false;
					} else {
						return false;
					}
				}
			}
		}
	}

	public String toString() {
		String odgovor;
		if (this.days == 0) {
			if (this.sign == true) {
				char znak = '+';
				odgovor = String.format("%c%02d:%02d", znak, hours, minutes);
				return odgovor;
			} else {
				char znak = '-';
				odgovor = String.format("%c%02d:%02d", znak, hours, minutes);
				return odgovor;
			}
		} else {
			if (this.sign == true) {
				char znak = '+';
				odgovor = String.format("%c%02d.%02d:%02d", znak, days, hours, minutes);
				return odgovor;
			} else {
				char znak = '-';
				odgovor = String.format("%c%02d.%02d:%02d", znak, days, hours, minutes);
				return odgovor;
			}
		}

	}

	// koji od 2 dana je veci
	public int kojDanVeci(Interval another) {
		int resenje;
		if (this.days > another.getDays()) {
			resenje = 1;
			return resenje;
		} else if (this.days < another.getDays()) {
			resenje = 2;
			return resenje;
		} else {
			resenje = 3;
			return resenje;
		}
	}

	// koji od 2 sata je veci
	public int kojSatVeci(Interval another) {
		int resenje;
		if (this.hours > another.getHours()) {
			resenje = 1;
			return resenje;
		} else if (this.hours < another.getHours()) {
			resenje = 2;
			return resenje;
		} else {
			resenje = 3;
			return resenje;
		}

	}

	// koji od 2 minuta je veci
	public int kojMinutVeci(Interval another) {
		int resenje;
		if (this.minutes > another.getMinutes()) {
			resenje = 1;
			return resenje;
		} else if (this.minutes < another.getMinutes()) {
			resenje = 2;
			return resenje;
		} else {
			resenje = 3;
			return resenje;
		}
	}

	public Interval sabiranje(Interval veci, Interval another) {
		int noviDani;
		int noviSati;
		int noviMinuti;
		int veci_sati = veci.getHours();
		int veci_dani = veci.getDays();
		int veci_minuti = veci.getMinutes();
		boolean noviSign;
		if (veci.getSign() == true) {
			noviSign = true;
		} else {
			noviSign = false;
		}
		if (veci_minuti >= another.getMinutes()) {
			noviMinuti = veci_minuti - another.getMinutes();
		} else {
			noviMinuti = (veci_minuti + 60) - another.getMinutes();
			veci_sati--;
		}
		if (veci_sati >= another.getHours()) {
			noviSati = veci_sati - another.getHours();
		} else {
			noviSati = (veci_sati + 24) - another.getHours();
			veci_dani--;
		}
		noviDani = veci_dani - another.getDays();
		Interval novi = new Interval(noviDani, noviSati, noviMinuti, noviSign);
		return novi;
	}

	public Interval oduzimanje(Interval prvi, Interval drugi) {
		int noviDani;
		int noviSati;
		int noviMinuti;
		boolean noviSign;
		int prvi_sati = prvi.getHours();
		int prvi_dani = prvi.getDays();
		int drugi_sati = drugi.getHours();
		int drugi_dani = drugi.getDays();
		if (prvi.greaterThan(drugi) == true) {
			noviSign = prvi.getSign();
		} else if (prvi.lessThan(drugi) == true && prvi.getSign() == true && drugi.getSign() == true) {
			noviSign = false;
		} else {
			noviSign = drugi.getSign();
		}

		if (prvi.getSign() == false && drugi.getSign() == false) {
			return prvi.add(drugi);
		}

		else if (prvi.lessThan(drugi)) {
			if (drugi.getMinutes() >= prvi.getMinutes()) {
				noviMinuti = drugi.getMinutes() - prvi.getMinutes();
			} else {
				noviMinuti = (drugi.getMinutes() + 60) - prvi.getMinutes();
				drugi_sati--;
			}
			if (drugi_sati >= prvi.getHours()) {
				noviSati = drugi_sati - prvi.getHours();
			} else {
				noviSati = (drugi_sati + 24) - prvi.getHours();
				drugi_dani--;
			}
			noviDani = drugi_dani - prvi.getDays();
			Interval novi = new Interval(noviDani, noviSati, noviMinuti, noviSign);
			return novi;
		}

		else {
			if (prvi.getMinutes() >= drugi.getMinutes()) {
				noviMinuti = prvi.getMinutes() - drugi.getMinutes();
			} else {
				noviMinuti = (prvi.getMinutes() + 60) - drugi.getMinutes();
				prvi_sati--;
			}
			if (prvi_sati >= drugi.getHours()) {
				noviSati = prvi_sati - drugi.getHours();
			} else {
				noviSati = (prvi_sati + 24) - drugi.getHours();
				prvi_dani--;
			}
			noviDani = prvi_dani - drugi.getDays();
			Interval novi = new Interval(noviDani, noviSati, noviMinuti, noviSign);
			return novi;
		}

	}
}
