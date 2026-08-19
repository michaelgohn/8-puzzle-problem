import { useState } from 'react'
import './App.css'
import { Puzzle } from './components/Puzzle'
import { BlankPuzzle } from './components/BlankPuzzle'
import { InputPuzzle } from './components/InputPuzzle'
import { LandingPage } from './components/LandingPage'
import { PuzzleList } from './components/PuzzleList'
import { Routes, Route } from "react-router-dom"
import { EditPuzzle } from './components/EditPuzzle'
import { SolvingPage } from './components/SolvingPage'
import { SolvedPage } from './components/SolvedPage'

function App() {

  return (
    <Routes>
      <Route path="/" element={ <LandingPage /> } />
      <Route path="/create" element={ <InputPuzzle /> } />
      <Route path="/view" element={ <PuzzleList /> } />
      <Route path="/edit/:id" element={ <EditPuzzle /> } />
      <Route path='/start/:id' element={ <SolvingPage /> } />
      <Route path='/solved' element={ <SolvedPage />} />
    </Routes>
  )
}

export default App
