export class Fuel {
  constructor(type, price) {
    this._type = type;
    this._price = price;
  }

  get type() {
    return this._type;
  }

  set type(type) {
    this._type = type;
  }

  get price() {
    return this._price;
  }

  set price(price) {
    this._price = price;
  }
}
