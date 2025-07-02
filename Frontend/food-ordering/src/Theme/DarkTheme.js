const { createTheme } = require=("@mui/material");

export const darkTheme = createTheme({
  palette: {
    mode: "dark",
    primary: {
      main: "#0b064d"
    },
    secondary: {
      main: "#6b0d1b",
    },
    background: {
        main: "#000000",
      default: "#0D0D0D",
      paper: "#0D0D0D"
    },
    text: {
      primary: "#ffffff"
    }
  }
})
