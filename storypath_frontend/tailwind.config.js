/** @type {import('tailwindcss').Config} */
export default {
  content: ["./index.html", "./src/**/*.{js,ts,jsx,tsx}"],
  theme: {
    extend: {
      colors: {
        lightPrimary: "rgb(209 213 219 / 1)",
        lightText: "rgb(31 41 55 / 1)",
        darkPrimary: "rgb(17 24 39 / 1)",
        darkText: "rgb(209 213 219 / 1)",
      },
      animation: {
        "infinite-scroll": "infinite-scroll 10s linear infinite",
      },
      keyframes: {
        "infinite-scroll": {
          "100%": { transform: "translate(-25%)" },
        },
      },
    },
  },
  darkMode: "class",
  plugins: [],
};
