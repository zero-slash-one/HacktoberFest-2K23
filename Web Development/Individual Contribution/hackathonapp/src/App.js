import Header from './components/Header'
import {Routes,Route} from 'react-router-dom';
import Landing from './components/Landing'
import Create from './components/Create'
import SingleHack from './components/SingleHack.jsx'
import Update from './components/Update'

function App() {
  return (
    <>
      <Header />
      
      <Routes>
        <Route path="/" exact element={<Landing />} ></Route>
        <Route path="/create" exact element={<Create/>} ></Route>
        <Route path="/hackathon/:id" exact element={<SingleHack/>} ></Route>
        <Route path="/update/:id" exact element={<Update/>} ></Route>
      </Routes>
      

    </>
  );
}

export default App;
