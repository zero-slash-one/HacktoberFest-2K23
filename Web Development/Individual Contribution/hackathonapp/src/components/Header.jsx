import React from 'react'
import {Link} from 'react-router-dom'

const Header = () => {
  return (
      <div className="h-14 shadow-md">
        <Link to="/">
          <div className="logo"></div>
        </Link>
      </div>
  )
}

export default Header