import React, { useEffect, useState } from 'react'
import Countdown from "react-countdown";
import { Link} from 'react-router-dom'

const Timer = ({ id ,start, end, title }) => {

    const [display, setDisplay] = useState("");
    const [head,setHead] = useState("")

    useEffect(() => {
        var s = new Date(start).getTime();
        var e = new Date(end).getTime();
        var c = new Date().getTime();
    
        if (c < s && c < e) {
            setDisplay("Starts in");
            setHead("Upcoming")
        }
         if (c > s && c < e) {
            setDisplay("Ended in")
            setHead("Active")
        }
         if (c > s && c > e) {
            setDisplay("Ended on")
            setHead("Past")
        }

    }, [start,end,head])

    const show = () => {
        var s = new Date(start).getTime();
        var e = new Date(end).getTime();
        var c = new Date().getTime();
        var sh, dis;
        if (c < s) {
            sh = s - c;
        }
        else if (c > s && c < e) {
            sh = e - c;
        }
        else if(c > s && c > e){
            sh = -1;
        }
        return sh;
    }

    const Completionist = () => {
        var ended = new Date(end)

        console.log(ended.toString())

        return <div className="text-center mt-4">
             <h1 className="text-basic">Ended On</h1>
            <p className="text-sm font-bold">{ended.toString()}</p>
            </div>

    }

    const renderer = ({ days, hours, minutes, seconds, completed }) => {
        if (show() < 0) {
            // Render a complete state
            return <Completionist />;
        }
        else{

        return <div className="mt-4">
            {display}
            <br />
            <div className="flex justify-center align-center">
                <div>
                    <div className="text-basic font-bold">{days}</div>
                    <p className="ml-2 text-sm" >Days</p>
                </div>
                <span>:</span>
                <div>
                    <div className="text-basic font-bold">{hours}</div>
                    <p className="ml-2 text-sm">Hours</p>
                </div>
                <span>:</span>
                <div>
                    <div className="text-basic font-bold">{minutes}</div>
                    <p className="ml-2 text-sm">Minutes</p>
                </div>
                <span>:</span>

                <div>
                    <div className="text-basic font-bold">{seconds}</div>
                    <p className="ml-2 text-sm">Seconds</p>
                </div>
            </div>
        </div>
        }
    };


    return (
        <div>
            <div className="h-6 text-sm rounded mt-2 mb-2 w-1/2 m-auto bg-g">{head}</div>
            <div className="mt-2 text-xl h-12 font-bold m-auto ">{title}</div>
            <Countdown  date={Date.now() + show()} renderer={renderer} />
            <br />
            <Link className="text-center text-white p-2 rounded bg-g font-bold mb-4" to={`/hackathon/${id}`} >Participate</Link>
        </div>
    )
}

export default Timer