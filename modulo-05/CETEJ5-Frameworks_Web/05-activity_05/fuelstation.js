export class FuelStation {
  constructor(name, gasoline, ethanol) {
    this._name = name;
    this._gasoline = gasoline;
    this._ethanol = ethanol;
  }

  get name() {
    return this._age;
  }

  set name(name) {
    this._name = name;
  }

  get gasoline() {
    return this._age;
  }

  set gasoline(gasoline) {
    this._gasoline = gasoline;
  }

  get ethanol() {
    return this._ethanol;
  }

  set ethanol(ethanol) {
    this._ethanol = ethanol;
  }

  suggestsFuel() {
    let calculation = (this._gasoline.price / this.ethanol.price).toFixed(2);
    let advantageousText =
      calculation < 0.7 ? 'advantageous' : 'disadvantageous';

    console.log(
      `Price of Gasoline / Price of Ethanol = ${calculation}.
Then, it is ${advantageousText} to fill up to Ethanol!`
    );
  }
}
