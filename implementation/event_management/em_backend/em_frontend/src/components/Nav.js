import React, { useEffect, useContext, useState } from "react";
import { Link } from "react-router-dom";

import { LOGO } from "../utils/statics";
import UserTileLogged from "./UserTileLogged";
import { loggedUser } from "../utils/utils";

function Nav() {
  return (
    <nav className="navbar navbar-expand-lg sticky-top bg-light">
      <div className="container-fluid">
        <a className="navbar-brand" href="/">
          EMS
        </a>
        <button
          className="navbar-toggler"
          type="button"
          data-bs-toggle="collapse"
          data-bs-target="#navbarSupportedContent"
          aria-controls="navbarSupportedContent"
          aria-expanded="false"
          aria-label="Toggle navigation"
        >
          <span className="navbar-toggler-icon"></span>
        </button>
        <div className="collapse navbar-collapse" id="navbarSupportedContent">
          <ul className="navbar-nav me-auto mb-2 mb-lg-0">
            <li className="nav-item">
              <a className="nav-link active" aria-current="page" href="/events">
                Home
              </a>
            </li>
            <li className="nav-item">
              <a className="nav-link" href="/bookmarks">
                Bookmarks
              </a>
            </li>
          </ul>
          <UserTileLogged
            userInfo={{
              first_name: loggedUser?.firstName,
              last_name: loggedUser?.lastName,
              roles: loggedUser?.roles,
            }}
          />
        </div>
      </div>
    </nav>
  );
}

export default Nav;
