package fr.epicanard.globalmarketchest.auctions;

public enum AuctionLoreConfig {
  ALL         ((short) 0b11111111101),
  TOSELL      ((short) 0b01111010101),
  OWN         ((short) 0b11110010101),
  SOLD        ((short) 0b11110111001),
  BOUGHT      ((short) 0b11111011001),
  BOUGHT_SOON ((short) 0b01111111001),
  OWNENDED    ((short) 0b11110011011),
  SELECTPRICE ((short) 0b01110000000)
  ;

  public Boolean state() {
    return (this.value & 1024) != 0;
  }

  public Boolean quantity() {
    return (this.value & 512) != 0;
  }

  public Boolean unitPrice() {
    return (this.value & 256) != 0;
  }

  public Boolean totalPrice() {
    return (this.value & 128) != 0;
  }

  public Boolean starter() {
    return (this.value & 64) != 0;
  }

  public Boolean ender() {
    return (this.value & 32) != 0;
  }

  public Boolean started() {
    return (this.value & 16) != 0;
  }

  public Boolean ended() {
    return (this.value & 8) != 0;
  }

  public Boolean expire() {
    return (this.value & 4) != 0;
  }

  public Boolean canceled() {
    return (this.value & 2) != 0;
  }

  public Boolean frame() {
    return (this.value & 1) != 0;
  }

  private short value;

  AuctionLoreConfig(short value) {
    this.value = value;
  }
}
