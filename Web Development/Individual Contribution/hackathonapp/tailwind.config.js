/** @type {import('tailwindcss').Config} */

module.exports = {
  mode : 'jit',
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",

  ],
  theme: {
    extend: {
      fontFamily:{
        'cinzel' :['Cinzel', 'serif'],
        'rb' : ['Roboto Flex', 'sans-serif']
      },
      backgroundImage : {
        Hero : "url('')"
      },
      colors : {
        primary : '#003145',
        secondary : '#002A3B',
        g : '#44924C'
      }
    },
  },
  plugins: [],
}