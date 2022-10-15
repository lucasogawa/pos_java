import { Fuel } from './fuel.js';
import { FuelStation } from './fuelstation.js';

new FuelStation(
  'Fuel Station 1',
  new Fuel(Fuel.GASOLINE, 80),
  new Fuel(Fuel.ETHANOL, 100)
).suggestsFuel();

let fuelStation2 = new FuelStation(
  'Fuel Station 2',
  new Fuel(Fuel.GASOLINE, 70),
  new Fuel(Fuel.ETHANOL, 100)
).suggestsFuel();

let fuelStation3 = new FuelStation(
  'Fuel Station 3',
  new Fuel(Fuel.GASOLINE, 60),
  new Fuel(Fuel.ETHANOL, 100)
).suggestsFuel();
