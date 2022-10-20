const obj = {
  name: "John",
  today: new Date().getFullYear(),
  city: "New York",
  pin: 480001,
};
const myJSON = JSON.stringify(obj);
console.log(myJSON);
