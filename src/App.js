import React from 'react';
import { BrowserRouter as Router, Routes, Route, Link } from 'react-router-dom';
import Aulas from './Aulas.js';
import Home from './Home.js';


function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/aulas" element={<Aulas />} />
      </Routes>
    </Router>
  );
}

export default App;