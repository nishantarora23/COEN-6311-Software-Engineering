import {BrowserRouter as Router, Route, Routes} from 'react-router-dom'
import Header from './components/Header'
import ManageProfile from './pages/ManageProfile'
import HomePage from './components/HomePage';

function App() {
  return (
    <Router>
      <Header />
      <div className='container'>
        <Routes>
          <Route exact path='/' element={<HomePage />} />
          <Route exact path='/edit_profile' element={<ManageProfile />} />
        </Routes>
      </div>
    </Router>
  );
}

export default App;
