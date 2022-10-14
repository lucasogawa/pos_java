import { Fuel } from './fuel.js';
import { FuelStation } from './fuelstation.js';

new FuelStation(
  'Fuel Station 1',
  new Fuel('Gasoline', 80),
  new Fuel('Ethanol', 100)
).suggestsFuel();

let fuelStation2 = new FuelStation(
  'Fuel Station 2',
  new Fuel('Gasoline', 70),
  new Fuel('Ethanol', 100)
).suggestsFuel();

let fuelStation3 = new FuelStation(
  'Fuel Station 3',
  new Fuel('Gasoline', 60),
  new Fuel('Ethanol', 100)
).suggestsFuel();
