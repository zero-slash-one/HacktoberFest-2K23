import React from 'react'
import Timer from './Timer'

const Hack = ({hack}) => {
  return (
 
     <div className="h-96  mt-5 mb-5 rounded bg-white">
        <div style={{backgroundImage : `url(" https://hackathondphi.herokuapp.com/api/photo/${hack._id}")`,backgroundSize:"cover"}} className="h-2/5 bg-white"></div>
        <div className="text-center h-3/5">
            <Timer id={hack._id} title={hack.title} start={hack.start} end={hack.end} />
        </div>
     </div>
  )
}

export default Hack;