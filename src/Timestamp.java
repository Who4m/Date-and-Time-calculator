
public class Timestamp {
	private int year;
	private int month;
	private int dayInMonth;
	private int hours;
	private int minutes;
	private Interval zoneOffset;

	public int getYear() {
		return year;
	}

	public int getMonth() {
		return month;
	}

	public int getDayInMonth() {
		return dayInMonth;
	}

	public int getHours() {
		return hours;
	}

	public int getMinutes() {
		return minutes;
	}

	public Interval getZoneOffset() {
		return zoneOffset;
	}

	public Timestamp(int year, int month, int dayInMonth, int hours, int minutes, Interval zoneOffset) {

		this.year = year;
		this.month = month;
		this.dayInMonth = dayInMonth;
		this.hours = hours;
		this.minutes = minutes;
		this.zoneOffset = new Interval(zoneOffset.getDays(), zoneOffset.getHours(), zoneOffset.getMinutes(),
				zoneOffset.getSign());
		if (zoneOffset.getDays() != 0) {
			System.out.println("mora da bude 0 dana");

		}
		if (zoneOffset.getHours() > 12) {
			System.out.println("mora da bude 0-12 sata");

		}
	}

	public Timestamp(int year, int month, int daysInMonth, Interval zoneOffset) {
		this.year = year;
		this.month = month;
		this.dayInMonth = daysInMonth;
		hours = 0;
		minutes = 0;
		this.zoneOffset = new Interval(zoneOffset.getDays(), zoneOffset.getHours(), zoneOffset.getMinutes(),
				zoneOffset.getSign());
	}

	public Timestamp add(Interval interval) {
		if (interval.getSign() == true) {
			return sabiranje(interval);
		} else {
			return oduzimanje(interval);
		}
	}

	public String toString() {
		String odgovor;
		odgovor = String.format("%4d-%02d-%02d %02d:%02d(UTC %s)", year, month, dayInMonth, hours, minutes,
				zoneOffset.toString());
		return odgovor;
	}

	public Timestamp subtract(Interval interval) {
		if (interval.getSign() == false) {
			return sabiranje(interval);
		} else {
			return oduzimanje(interval);
		}
	}

	public Timestamp sabiranje(Interval interval) {
		int novaGodina = this.year;
		int noviMesec = this.month;
		int noviDan = this.dayInMonth;
		int noviSat = this.hours;
		int noviMinut = this.minutes;
		int dodajemDani = interval.getDays();
		noviMinut += interval.getMinutes();
		if (noviMinut >= 60) {
			noviMinut = noviMinut - 60;
			noviSat++;
		}
		noviSat += interval.getHours();
		if (noviSat >= 24) {
			noviSat = noviSat - 24;
			noviDan++;
		}
		do {
			if (noviMesec == 1) {
				if (noviDan + dodajemDani > 31) {
					dodajemDani = dodajemDani - (31 - noviDan);
					noviMesec++;
					noviDan = 0;
				} else {
					noviDan += dodajemDani;
					break;
				}
			}
			if (noviMesec == 2) {
				if ((novaGodina % 4 == 0 && novaGodina % 100 != 0) || novaGodina % 400 == 0) {
					if (noviDan + dodajemDani > 29) {
						dodajemDani = dodajemDani - (29 - noviDan);
						noviMesec++;
						noviDan = 0;
					} else {
						noviDan += dodajemDani;
						break;
					}
				} else {
					if (noviDan + dodajemDani > 28) {
						dodajemDani = dodajemDani - (28 - noviDan);
						noviMesec++;
						noviDan = 0;
					} else {
						noviDan += dodajemDani;
						break;
					}
				}
			}

			if (noviMesec == 3) {
				if (noviDan + dodajemDani > 31) {
					dodajemDani = dodajemDani - (31 - noviDan);
					noviMesec++;
					noviDan = 0;
				} else {
					noviDan += dodajemDani;
					break;
				}
			}
			if (noviMesec == 4) {
				if (noviDan + dodajemDani > 30) {
					dodajemDani = dodajemDani - (30 - noviDan);
					noviMesec++;
					noviDan = 0;
				} else {
					noviDan += dodajemDani;
					break;
				}
			}
			if (noviMesec == 5) {
				if (noviDan + dodajemDani > 31) {
					dodajemDani = dodajemDani - (31 - noviDan);
					noviMesec++;
					noviDan = 0;
				} else {
					noviDan += dodajemDani;
					break;
				}
			}
			if (noviMesec == 6) {
				if (noviDan + dodajemDani > 30) {
					dodajemDani = dodajemDani - (30 - noviDan);
					noviMesec++;
					noviDan = 0;

				} else {
					noviDan += dodajemDani;
					break;

				}
			}
			if (noviMesec == 7) {
				if (noviDan + dodajemDani > 31) {
					dodajemDani = dodajemDani - (31 - noviDan);
					noviMesec++;
					noviDan = 0;
				} else {
					noviDan += dodajemDani;
					break;
				}
			}
			if (noviMesec == 8) {
				if (noviDan + dodajemDani > 31) {
					dodajemDani = dodajemDani - (31 - noviDan);
					noviMesec++;
					noviDan = 0;
				} else {
					noviDan += dodajemDani;
					break;
				}
			}
			if (noviMesec == 9) {
				if (noviDan + dodajemDani > 30) {
					dodajemDani = dodajemDani - (30 - noviDan);
					noviMesec++;
					noviDan = 0;
				} else {
					noviDan += dodajemDani;
					break;
				}
			}
			if (noviMesec == 10) {
				if (noviDan + dodajemDani > 31) {
					dodajemDani = dodajemDani - (31 - noviDan);
					noviMesec++;
					noviDan = 0;
				} else {
					noviDan += dodajemDani;
					break;
				}
			}
			if (noviMesec == 11) {
				if (noviDan + dodajemDani > 30) {
					dodajemDani = dodajemDani - (30 - noviDan);
					noviMesec++;
					noviDan = 0;
				} else {
					noviDan += dodajemDani;
					break;
				}
			}
			if (noviMesec == 12) {
				if (noviDan + dodajemDani > 31) {
					dodajemDani = dodajemDani - (31 - noviDan);
					noviMesec = 1;
					noviDan = 0;
					novaGodina++;
				} else {
					noviDan += dodajemDani;
					break;
				}
			}
		} while (dodajemDani != 0);
		Timestamp aa = new Timestamp(novaGodina, noviMesec, noviDan, noviSat, noviMinut, zoneOffset);
		return aa;

	}

	public Timestamp oduzimanje(Interval interval) {
		int novaGodina = this.year;
		int noviMesec = this.month;
		int noviDan = this.dayInMonth;
		int noviSat = this.hours;
		int noviMinut = this.minutes;
		int oduzimamDani = interval.getDays();

		if (noviMinut >= interval.getMinutes()) {
			noviMinut -= interval.getMinutes();
		} else {
			noviMinut = (noviMinut + 60) - interval.getMinutes();
			noviSat--;
		}
		if (noviSat >= interval.getHours()) {
			noviSat -= interval.getHours();
		} else {
			noviSat = (noviSat + 24) - interval.getHours();
			noviDan--;
		}
		do {
			if (noviMesec == 1) {
				if (noviDan - oduzimamDani <= 0) {
					oduzimamDani = oduzimamDani - noviDan;
					noviMesec = 12;
					noviDan = 31;
					novaGodina--;
				} else {
					noviDan = noviDan - oduzimamDani;
					break;
				}
			}
			if (noviMesec == 2) {
				if (noviDan - oduzimamDani <= 0) {
					oduzimamDani = oduzimamDani - noviDan;
					noviDan = 31;
					noviMesec = 1;
				} else {
					noviDan = noviDan - oduzimamDani;
					break;
				}
			}

			if (noviMesec == 3) {
				if ((novaGodina % 4 == 0 && novaGodina % 100 != 0) || novaGodina % 400 == 0) {
					if (noviDan - oduzimamDani <= 0) {
						oduzimamDani -= noviDan;
						noviDan = 29;
						noviMesec = 2;
					} else {
						noviDan = noviDan - oduzimamDani;
						break;
					}
				} else {
					if (noviDan - oduzimamDani <= 0) {
						oduzimamDani -= noviDan;
						noviDan = 28;
						noviMesec = 2;
					} else {
						noviDan = noviDan - oduzimamDani;
						break;
					}
				}
			}
			if (noviMesec == 4) {
				if (noviDan - oduzimamDani <= 0) {
					oduzimamDani -= noviDan;
					noviDan = 31;
					noviMesec = 3;
				} else {
					noviDan = noviDan - oduzimamDani;
					break;
				}
			}
			if (noviMesec == 5) {
				if (noviDan - oduzimamDani <= 0) {
					oduzimamDani -= noviDan;
					noviDan = 30;
					noviMesec = 4;
				} else {
					noviDan = noviDan - oduzimamDani;
					break;
				}
			}
			if (noviMesec == 6) {
				if (noviDan - oduzimamDani <= 0) {
					oduzimamDani -= noviDan;
					noviDan = 31;
					noviMesec = 5;
				} else {
					noviDan = noviDan - oduzimamDani;
					break;
				}
			}
			if (noviMesec == 7) {
				if (noviDan - oduzimamDani <= 0) {
					oduzimamDani -= noviDan;
					noviDan = 30;
					noviMesec = 6;
				} else {
					noviDan = noviDan - oduzimamDani;
					break;
				}
			}
			if (noviMesec == 8) {
				if (noviDan - oduzimamDani <= 0) {
					oduzimamDani -= noviDan;
					noviDan = 31;
					noviMesec = 7;
				} else {
					noviDan = noviDan - oduzimamDani;
					break;
				}
			}
			if (noviMesec == 9) {
				if (noviDan - oduzimamDani <= 0) {
					oduzimamDani -= noviDan;
					noviDan = 31;
					noviMesec = 8;

				} else {
					noviDan = noviDan - oduzimamDani;
					break;
				}
			}
			if (noviMesec == 10) {
				if (noviDan - oduzimamDani <= 0) {
					oduzimamDani -= noviDan;
					noviDan = 30;
					noviMesec = 9;
				} else {
					noviDan = noviDan - oduzimamDani;
					break;
				}
			}
			if (noviMesec == 11) {
				if (noviDan - oduzimamDani <= 0) {
					oduzimamDani -= noviDan;
					noviDan = 31;
					noviMesec = 10;
				} else {
					noviDan = noviDan - oduzimamDani;
					break;
				}
			}
			if (noviMesec == 12) {
				if (noviDan - oduzimamDani <= 0) {
					oduzimamDani -= noviDan;
					noviDan = 30;
					noviMesec = 11;
				} else {
					noviDan = noviDan - oduzimamDani;
					break;
				}
			}
		} while (oduzimamDani != 0);

		Timestamp novo = new Timestamp(novaGodina, noviMesec, noviDan, noviSat, noviMinut, zoneOffset);
		return novo;
	}

	public Interval subtract(Timestamp another) {
		if (equals(another) == true) {
			Interval odgovor = new Interval(0, 0, 0, true);

			return odgovor;
		} else if (greaterThan(another) == true) {
			Interval odgovor = new Interval(racunam(this, another).getDays(), racunam(this, another).getHours(),
					racunam(this, another).getMinutes(), true);
			return odgovor;
		} else {
			Interval odgovor = new Interval(racunam(another, this).getDays(), racunam(another, this).getHours(),
					racunam(another, this).getMinutes(), false);
			return odgovor;
		}
	}

	public Interval racunam(Timestamp veci, Timestamp manji) {
		int veci_godina = veci.racunajOffset().getYear();
		int veci_mesec = veci.racunajOffset().getMonth();
		int veci_dan = veci.racunajOffset().getDayInMonth();
		int veci_sat = veci.racunajOffset().getHours();
		int veci_minut = veci.racunajOffset().getMinutes();
		int manji_godina = manji.racunajOffset().getYear();
		int manji_mesec = manji.racunajOffset().getMonth();
		int manji_dan = manji.racunajOffset().getDayInMonth();
		int manji_sat = manji.racunajOffset().getHours();
		int manji_minut = manji.racunajOffset().getMinutes();
		boolean noviSign = true;
		int novi_dan = 0;
		int novi_sat = 0;
		int novi_minut = 0;
		while (veci_godina != manji_godina || veci_mesec != manji_mesec || veci_dan != manji_dan) {
			novi_dan++;
			if (manji_mesec == 1 && manji_dan >= 31) {
				manji_dan = 0;
				manji_mesec = 2;
			}

			if ((manji_godina % 4 == 0 && manji_godina % 100 != 0) || manji_godina % 400 == 0) {
				if (manji_mesec == 2 && manji_dan >= 29) {
					manji_dan = 0;
					manji_mesec = 3;
				}
			} else {
				if (manji_mesec == 2 && manji_dan >= 28) {
					manji_dan = 0;
					manji_mesec = 3;
				}
			}
			if (manji_mesec == 3 && manji_dan >= 31) {
				manji_dan = 0;
				manji_mesec = 4;
			}
			if (manji_mesec == 4 && manji_dan >= 30) {
				manji_dan = 0;
				manji_mesec = 5;
			}
			if (manji_mesec == 5 && manji_dan >= 31) {
				manji_dan = 0;
				manji_mesec = 6;
			}
			if (manji_mesec == 6 && manji_dan >= 30) {
				manji_dan = 0;
				manji_mesec = 7;
			}
			if (manji_mesec == 7 && manji_dan >= 31) {
				manji_dan = 0;
				manji_mesec = 8;
			}
			if (manji_mesec == 8 && manji_dan >= 31) {
				manji_dan = 0;
				manji_mesec = 9;
			}
			if (manji_mesec == 9 && manji_dan >= 30) {
				manji_dan = 0;
				manji_mesec = 10;
			}
			if (manji_mesec == 10 && manji_dan >= 31) {
				manji_dan = 0;
				manji_mesec = 11;
			}
			if (manji_mesec == 11 && manji_dan >= 30) {
				manji_dan = 0;
				manji_mesec = 12;
			}
			if (manji_mesec == 12 && manji_dan >= 31) {
				manji_dan = 0;
				manji_mesec = 1;
				manji_godina++;
			}
			manji_dan++;
		}
		if (veci_minut >= manji_minut) {
			novi_minut = veci_minut - manji_minut;

		} else {
			novi_minut = (veci_minut + 60) - manji_minut;
			veci_sat--;
		}
		if (veci_sat >= manji_sat) {
			novi_sat = veci_sat - manji_sat;
		} else {
			novi_sat = (veci_sat + 24) - manji_sat;
			novi_dan--;
		}
		Interval razlika = new Interval(novi_dan, novi_sat, novi_minut, noviSign);
		return razlika;
	}

	public boolean equals(Timestamp another) {
		if (racunajOffset().getYear() == another.racunajOffset().getYear()
				&& racunajOffset().getMonth() == another.racunajOffset().getMonth()
				&& racunajOffset().getDayInMonth() == another.racunajOffset().getDayInMonth()
				&& racunajOffset().getHours() == another.racunajOffset().getHours()
				&& racunajOffset().getMinutes() == another.racunajOffset().getMinutes()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean lessThan(Timestamp another) {
		if (racunajOffset().getYear() < another.racunajOffset().getYear()) {
			return true;
		} else if (racunajOffset().getYear() > another.racunajOffset().getYear()) {
			return false;
		} else {
			if (racunajOffset().getMonth() < another.racunajOffset().getMonth()) {
				return true;
			} else if (racunajOffset().getMonth() > another.racunajOffset().getMonth()) {
				return false;
			} else {
				if (racunajOffset().getDayInMonth() < another.racunajOffset().getDayInMonth()) {
					return true;
				} else if (racunajOffset().getDayInMonth() > another.racunajOffset().getDayInMonth()) {
					return false;
				} else {
					if (racunajOffset().getHours() < another.racunajOffset().getHours()) {
						return true;
					} else if (racunajOffset().getHours() > another.racunajOffset().getHours()) {
						return false;
					} else {
						if (racunajOffset().getMinutes() < another.racunajOffset().getMinutes()) {
							return true;
						} else if (racunajOffset().getMinutes() > another.racunajOffset().getMinutes()) {
							return false;
						} else {
							return false;
						}
					}
				}
			}
		}
	}

	public boolean greaterThan(Timestamp another) {
		if (equals(another) == true) {
			return false;
		} else if (lessThan(another) == true) {
			return false;
		} else {
			return true;
		}
	}

	public Timestamp racunajOffset() {
		if (zoneOffset.getSign() == true) {
			return oduzimanje(zoneOffset);
		} else {
			return sabiranje(zoneOffset);
		}
	}
}
