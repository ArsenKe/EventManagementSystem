import React from "react";
import { Link } from "react-router-dom";
import { NOT_FOUND_IMG } from "../utils/statics";

function NotFound404Page() {
  return (
    <div className="container text-center">
      <div className="d-flex align-items-center justify-content-center vh-100">
        <div className="text-center row">
          <div className="col-md-6">
            <img
              src={process.env.PUBLIC_URL + NOT_FOUND_IMG}
              alt=""
              width={500}
              className="img-fluid"
            />
          </div>
          <div className=" col-md-6 mt-5">
            <p className="fs-3">
              {" "}
              <span className="text-danger">Opps!</span> Page not found.
            </p>
            <p className="lead">The page you’re looking for doesn’t exist.</p>
            <Link className="btn btn-primary" to="/">
              Go Home
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
}

export default NotFound404Page;
