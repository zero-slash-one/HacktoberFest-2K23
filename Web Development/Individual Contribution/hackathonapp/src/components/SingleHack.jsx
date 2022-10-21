import React, { useState, useEffect } from 'react'
import { useParams, Link, useNavigate } from 'react-router-dom'
import { RotatingLines } from 'react-loader-spinner'

const SingleHack = () => {
    let navigate = useNavigate();
    const [hack, setHack] = useState({});
    const [dis, setDis] = useState("");
    let { id } = useParams();
    const { title, start, end, description, level } = hack;
    useEffect(() => {
        show();
        fetch(` https://hackathondphi.herokuapp.com/api/hackathon/${id}`).then(data => {
            return data.json();
        }).then((h) => {
            console.log(h);
            setHack(h)
        })
    }, [hack])

    const show = () => {
        var s = new Date(parseInt(start)).toLocaleString()
        var e = new Date(parseInt(end)).toLocaleString();
        var c = new Date().getTime();
        var a = parseInt(start)
        var b = parseInt(end);

        if (a > c) {
            setDis(`Starts on ${s}`);
        }
        else if (a < c && c < b) {
            setDis(`Ended in ${e}`)
        }
        else if (c > b) {
            setDis(`Ended on ${e}`)
        }
    }

    const deleteHack = () => {
        fetch(` https://hackathondphi.herokuapp.com/api/delete/${hack._id}`, {
            method: "DELETE",
            headers: {
                Accept: 'application/json'
            }
        }).then((res) => {
            return res.json();

        })
        .then((result)=>{
              if(result.message){
                navigate('/')
              }
        })
        .catch(err => {
            console.log(err)
        })
    }

    const showData = ()=>{
   if(!hack){
    return <div className="bg-primary h-48 flex justify-center">
    <RotatingLines

        strokeColor="grey"
        strokeWidth="3"
        animationDuration="0.75"
        width="80"
        visible={true}
    />
</div>
   }
        return  <div className="h-screen">
        <div className="h-2/5 bg-primary">
            <div className="grid items-center">
                <div className="mt-10 ml-20 h-6 text-center w-72 font-semibold bg-white rounded">{dis}</div>
                <div className="mt-6 ml-20 text-3xl font-bold text-white">{title}</div>
                <div className="h-8 w-20 rounded mt-6 ml-20 text-center bg-white text-basic font-bold">{level}</div>
            </div>
        </div>
        <div className="shadow h-12 flex justify-between">
            <div className="border-b-4 text-basic font-bold border-g ml-20 mt-4">Overview</div>
            <div className="flex mr-20 justify-center items-center">
                <Link to={`/update/${hack._id}`} className="h-8 w-12 grid content-center text-center bg-g rounded text-white text-sm font-bold">Edit</Link>
                <button onClick={deleteHack} className="h-8 w-14 grid content-center ml-5 text-center border-2 border-red-500 rounded text-red-600 text-sm font-bold">Delete</button>
            </div>
        </div>
        <div className="h-1/2 m-20 text-sm">{description}</div>
    </div>
    }


    return (
      <div>{showData()}</div>
    )
}

export default SingleHack