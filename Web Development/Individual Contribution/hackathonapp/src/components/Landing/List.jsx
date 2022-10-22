import React, { useState, useEffect } from 'react'
import Hack from './Hack'
import OutlinedInput from '@mui/material/OutlinedInput';
import InputLabel from '@mui/material/InputLabel';
import MenuItem from '@mui/material/MenuItem';
import FormControl from '@mui/material/FormControl';
import ListItemText from '@mui/material/ListItemText';
import Select from '@mui/material/Select';
import Checkbox from '@mui/material/Checkbox';
import { RotatingLines } from 'react-loader-spinner'


const ITEM_HEIGHT = 80;
const ITEM_PADDING_TOP = 2;
const MenuProps = {
    PaperProps: {
        style: {
            maxHeight: ITEM_HEIGHT * 4.5 + ITEM_PADDING_TOP,
            width: 200,
        },
    },
};

const names = [
    'upcoming',
    'active',
    'past',
    'easy',
    'medium',
    'hard'
];


const List = () => {
    const [hackathons, setHack] = useState([]);
    const [query, setQuery] = useState("")
    const [Final, setFinal] = useState([]);
    const [selected, setSelected] = React.useState([]);

    const handleChange2 = (event) => {
        const {
            target: { value },
        } = event;
        setSelected(
            // On autofill we get a stringified value.
            typeof value === 'string' ? value.split(',') : value,
        );
    };

    const handleChange = (e) => {
        setQuery(e.target.value);
    }

    useEffect(() => {

       
        fetch(' https://hackathondphi.herokuapp.com/api/hackathons', {
        method: 'GET',
        }).then((data) => {
            return data.json();
        })
            .then(data => {
                setHack(data.hackathons)
            })
            .catch(err => {
                console.log(err)
            })
        
        let updatedHack = hackathons;
        let final = [];

        if (query) {
            updatedHack = updatedHack.filter((item) => {
                return item.title.toLowerCase().includes(query)
            }
            )
        }

        if (selected.length > 0 ) {
            selected.forEach((item) => {
                if (item === "easy") {
                    hackathons.forEach(h => {
                        if (h.level === "Easy") {
                            final.push(h);
                        }
                    })
                }
                if (item === "medium") {
                    hackathons.forEach(h => {
                        if (h.level === "Medium") {
                            final.push(h);
                        }
                    })
                }
                if (item === "hard") {
                    hackathons.forEach(h => {
                        if (h.level === "Hard") {
                            final.push(h);
                        }
                    })
                }
                if (item === "upcoming") {
                    let c = new Date().getTime();
                hackathons.forEach(h => {
                        let s = new Date(h.start).getTime();
                        let e = new Date(h.end).getTime();
                        if (s > c && e > c) {
                            final.push(h);
                        }
                    })
                }
                if (item === "active") {
                    let c = new Date().getTime();
                    hackathons.forEach(h => {
                        let s = new Date(h.start).getTime();
                        let e = new Date(h.end).getTime();
                        if (s < c && e > c) {
                            final.push(h);
                        }
                    })
                }
                if (item === "past") {
                    let c = new Date().getTime();
                    hackathons.forEach(h => {
                        let s = new Date(h.start).getTime();
                        let e = new Date(h.end).getTime();
                        if (s < c && e < c) {
                            final.push(h);
                        }
                    })
                }
            })
        }

        if(selected.length > 0){
            console.log(final)
            setHack(final)
        }
        if(query){
            setHack(updatedHack)
        }

    }, [hackathons])

    const hideFilter = (i) => {
        console.log(i);
        let s = selected.filter((item, j) => {
            return (j != i);
        })
        console.log(s);
        setSelected(s);
    }


    const showList = () => {


        if (hackathons.length == 0) {

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

        else {

            return <div className="ml-40 mr-40 grid md:grid-cols-3 gap-10">
                {hackathons.map((item,i) => (
                    <Hack key={i} hack={item} />
                ))
                }
            </div>
        }

    }

    return (
        <div className="">
            <div className="flex justify-center flex-col h-56 bg-secondary">
                <div className="mt-4 text-white text-3xl font-bold ml-auto mr-auto mb-4">Explore Challenges</div>
                <div className="flex">
                    <div class="w-full text-center mb-3 md:mb-0">
                        <input class="appearance-none m-auto mt-3 block w-3/4 bg-gray-200 text-gray-700 border rounded py-3 px-3 mb-3 leading-tight focus:outline-none focus:bg-white" id="grid-first-name" type="text" placeholder="Search" onChange={handleChange} />
                    </div>
                    <div style={{ background: "white", borderRadius: "10px", position: "relative", right: "120px", height: "50px", top: "15px" }} className="mb-8" >
                        <FormControl style={{ border: "1px solid white", marginTop: "4px" }} sx={{ m: 1, width: 100, }} size="small">
                            <InputLabel style={{ color: "" }} id="demo-multiple-checkbox-label">Filter</InputLabel>
                            <Select
                                labelId="demo-multiple-checkbox-label"
                                id="demo-multiple-checkbox"
                                multiple
                                value={selected}
                                onChange={handleChange2}
                                input={<OutlinedInput label="Tag" />}
                                renderValue={(se) => se.join(', ')}
                                MenuProps={MenuProps}
                            >
                                {names.map((name) => (
                                    <MenuItem key={name} value={name}>
                                        <Checkbox checked={selected.indexOf(name) > -1} />
                                        <ListItemText primary={name} />
                                    </MenuItem>
                                ))}
                            </Select>
                        </FormControl>
                    </div>
                </div>
                <div className="space-x-2 mt-2 m-auto flex justify-center items-center">
                    {selected.map((item, i) => (
                        <div style={{ background: "rgba(248, 249, 253, 0.49)" }} className="space-x-2 flex justify-center items-center h-10 w-28 rounded">
                            <div className="text-white font-bold">{item}</div>
                            <button key={i} onClick={() => hideFilter(i)} className="h-4 w-4 text-center cursor-pointer flex justify-center items-center rounded-lg bg-white">
                                <i className="cursor-pointer text-sm text-gray-500 fa fa-times"></i>
                            </button>
                        </div>
                    ))
                    }
                </div>
            </div>
            <div className="h-max bg-primary">

                {showList()}

            </div>
        </div>
    )
}


export default List