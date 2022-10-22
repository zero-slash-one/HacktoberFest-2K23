import React from 'react'
import art from '../../assests/art.svg'
import h1 from '../../assests/h1.svg'
import h2 from '../../assests/h2.svg'
import h3 from '../../assests/h3.svg'
import { Link } from 'react-router-dom'

const Static = () => {
    return (
        <div className="home">
            <div className="h-3/4 bg-primary grid grid-cols-3 content-center">
                <div className="col-span-2 m-20">
                    <div className="text-3xl font-bold text-white w-2/3">
                        Accelerate Innovation with Global AI Challenges
                    </div>
                    <div className="text-basic text-white mt-8">
                        Lorem, ipsum dolor sit amet consectetur adipisicing elit. A, consequatur ad velit maiores tempora deserunt, consequuntur et sed illo tenetur quae, esse cum at! Quis repellat unde exercitationem ex eligendi.
                    </div>
                    <Link to="/create" className="mt-6 h-12 text-center grid content-center cursor-pointer font-bold rounded-lg w-48 bg-white">Create Challenges</Link>
                </div>
                <div className="col-span-1">
                    <img src={art} alt="" />
                </div>
            </div>
            <div className="grid grid-cols-3 h-1/4 bg-secondary content-center">
                <div className="flex justify-center items-center">
                    <img className="h-12" src={h1} alt="" />
                    <div className="">
                        <h1 className="text-xl ml-2 mt-2 font-bold text-white">100K+</h1>
                        <p className="text-sm text-white ml-2 mb-2">AI model Submission</p>
                    </div>
                    <div className="h-8 ml-14 border bg-white"></div>
                </div>
                <div className="flex justify-center items-center">
                    <img className="h-12" src={h2} alt="" />
                    <div className="">
                        <h1 className="text-xl ml-2 mt-2 font-bold text-white">50K+</h1>
                        <p className="text-sm text-white ml-2 mb-2">Data Scientists</p>
                    </div>
                    <div className="h-8 ml-14 border bg-white"></div>
                </div>
                <div className="flex justify-center items-center">
                    <img className="h-12" src={h3} alt="" />
                    <div className="">
                        <h1 className="text-xl ml-2 mt-2 font-bold text-white">100</h1>
                        <p className="text-sm text-white ml-2 mb-2">AI Challenges hosted</p>
                    </div>
                </div>
              
            </div>
        </div>
    )
}

export default Static;