import { useState } from 'react'
import './App.css'
import { Puzzle } from './components/Puzzle'
import { BlankPuzzle } from './components/BlankPuzzle'
import { InputPuzzle } from './components/InputPuzzle'

function App() {

  return (
    <>
      <InputPuzzle />
      <Puzzle></Puzzle>
    </>
  )
}

export default App
