import { lightBlue } from '@mui/material/colors';
import { createTheme, ThemeProvider, StyledEngineProvider } from '@mui/material/styles';
import ReactDOM from 'react-dom/client';
import { IntlProvider } from 'react-intl';
import App from './App';
import './index.css';
import English from './translations/en.json';
import variables from './variables.scss';

const root = ReactDOM.createRoot(document.getElementById('root') as HTMLElement);

const locale = navigator.language;

const theme = createTheme({
  palette: {
    primary: {
      main: '#ffffff'
    },
    secondary: {
      main: lightBlue[100]
    }
  },
  components: {
    MuiBottomNavigation: {
      styleOverrides: {
        root: {
          backgroundColor: '#ffffff',
          color: 'black'
        }
      }
    },
    MuiButton: {
      styleOverrides: {
        root: {
          backgroundColor: '#1d1e25!important',
          color: 'white!important',
          borderColor:'#1d1e25!important',
          padding:'10px 10px',
          textTransform:'capitalize'
        },
      },
    },
    MuiTextField: {
      styleOverrides: {
        root: {
          '& label': {
            color: 'black', // Change label color
          },
          '& .MuiOutlinedInput-root': {
            '& fieldset': {
              borderColor: 'black', // Border color
            },
            '&.Mui-focused fieldset': {
              borderColor: 'black', // Border color when focused
            },
          },
        },
      },
    },
  },
  typography: {
    h1: {
      fontSize: '2.5rem',
      fontWeight: 400
    },
    h2: {
      fontSize: '1.2rem',
      fontWeight: 300,
      color: variables.GREY_03
    },
    h3: {
      fontSize: '1.2rem',
      fontWeight: 'bold'
    }
  }
});

root.render(
  <StyledEngineProvider injectFirst>
    <ThemeProvider theme={theme}>
      <IntlProvider locale={locale} messages={English}>
        <App />
      </IntlProvider>
    </ThemeProvider>
  </StyledEngineProvider>
);
