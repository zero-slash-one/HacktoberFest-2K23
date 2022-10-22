import React from 'react'
import h1 from '../../assests/h1.svg'

const Participation = () => {
  return (
    <div className='h-max pb-8  mt-8  m-4'>
                <h1 className='text-center text-2xl font-bold'>Why Participate in <span className='text-lime-400	'> AI Challanges?</span></h1>
                <div className='grid  grid-cols-2 justify-around mt-4
                md:ml-8 md:m-8   '>
                    <div className='md:col-span-1 p-4 bg-slate-100 rounded w-2/3 m-auto mt-4  col-span-2 '>
                        <img src={h1} alt="" />
                        <p className='font-bold'>Prove your skills</p>
                        <p>Gain substantial experience by solving real-wrold problems
                            and pit agains others to come up with innovative solutions.

                        </p>
                    </div>
                    <div className='md:col-span-1 p-4 bg-slate-100 rounded w-2/3 m-auto mt-4 col-span-2'>
                        <img src={h1} alt="" />
                        <p className='font-bold'>Learn from community</p>
                        <p>One can look and analyze the solutions submitted by the other Data Scientist in the community and learn from them.

                        </p>
                    </div>
                    <div className='md:col-span-1 bg-slate-100 rounded p-4 w-2/3 m-auto mt-4 col-span-2'>
                        <img src={h1} alt="" />
                        <p className='font-bold'>Challenge yourself</p>
                        <p>
                            There is nothing for you to lose by participating in a challenge.You can fall safe, learn out of the entire experince and bounce back harder

                        </p>
                    </div>
                    <div className='md:col-span-1 bg-slate-100 rounded p-4 w-2/3 m-auto mt-4 col-span-2'>
                        <img src={h1} alt="" />
                        <p className='font-bold'>Earn recognition</p>
                        <p>
                            You will stand out from the crows if you do well in AI challanges,it noy only helps you shine in the community but also earns rewards.

                        </p>
                    </div>
                </div>
            </div>
  )
}

export default Participation