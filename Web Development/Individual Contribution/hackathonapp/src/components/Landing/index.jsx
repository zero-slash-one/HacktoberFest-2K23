import React,{useState,useEffect} from 'react'
import { Link } from 'react-router-dom'
import Participation from './Participation'
import Static from './Static'
import List from './List'

const Home = () => {

    return (
        <>
        <Static />
        <Participation />
        <List/>


      </>
    )
}

export default Home;