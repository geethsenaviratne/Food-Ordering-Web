import { CssBaseline, Typography, Container } from '@mui/material';
import { ThemeProvider, createTheme } from '@mui/material/styles';
import { Navbar } from './component/Navbar'; 

const darkTheme = createTheme({
  palette: {
    mode: 'dark',
  },
});

function App() {
  return (
    <ThemeProvider theme={darkTheme}>
      <CssBaseline />
      <Navbar />
    </ThemeProvider>
  );
}

export default App;
