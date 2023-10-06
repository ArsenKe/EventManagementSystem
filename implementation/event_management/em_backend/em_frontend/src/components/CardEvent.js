import { Link } from "react-router-dom";
import TimeAgo from "./TimeAgo";
import { useEffect, useState } from "react";
import { toast } from "react-toastify";
const CardEvent = ({ event, onClickMark, isMarked = false, isCompact = false }) => {
  const toastMessage = (title) => toast("Event saved to bookmark list");

  return isCompact === false ? (
    <>
      <div className="col-xs-12 col-sm-6 col-md-4 col-lg-3 col-xs-3">
        <div className="card border-0 shadow my-3">
          <div className="card-body">
            <h5 className="card-title text-truncate">{event?.eventName}</h5>
            <p className="card-text text-truncate">{event?.description}</p>
            <p className="my-1">
              <i className="bi bi-person-lock"></i> {event?.capacity}
            </p>
            <p className="my-1">
              <i className="bi bi-pin-map-fill"></i> {event?.location?.street}{" "}
              &nbsp;
              {event?.location?.streetNumber}, &nbsp;
              {event?.location?.city},&nbsp;
              {event?.location?.country}
            </p>
            <TimeAgo prefix="Start: " timestamp={event?.startDatetime} />
            <br />
            <TimeAgo prefix="End: " timestamp={event?.endDatetime} />

            <div className="tags mt-2">
              <i className="bi bi-tags"> </i>
              {event?.tags?.map((item, index) => {
                return (
                  <button
                    key={index}
                    className="badge text-bg-info bg-opacity-10 text-info border-0 mx-1"
                  >
                    {item?.name + " "}
                    <span className="text-danger"></span>
                  </button>
                );
              })}
            </div>

            <hr className="mt-4 m-1" />
            {/* buttons -----------------------------------------------*/}
            <div className="row d-flex justify-content-between">
              <div className="col-auto">
                <Link to={"/events/" + event?.id}>
                  <button type="button" className="btn btn-sm btn-outline-info">
                    <i className="bi bi-zoom-in"></i> See details
                  </button>
                </Link>
              </div>

              <div className="col-auto">
                <div
                  className="btn-group btn-group-sm"
                  role="group"
                  aria-label="Small button group"
                >
                  <button
                    type="button"
                    onClick={onClickMark}
                    className="btn btn-outline-secondary"
                  >
                    {isMarked === true ? (
                      <i className="bi bi-bookmark-fill text-warning"></i>
                    ) : (
                      <i className="bi bi-bookmark"></i>
                    )}
                  </button>
                </div>
              </div>
            </div>
          </div>
          {/* <div className="card-footer text-muted">
            <TimeAgo prefix="Last updated " timestamp={event?.start_datetime} />
          </div> */}
        </div>
      </div>
    </>
  ) : (
    <>
      <div className="col-xs-12">
        <div className="card border-0 shadow my-2">
          <div className="card-body">
            <h5 className="card-title text-truncate mb-0 pb-0">
              {event?.eventName}
            </h5>
            <TimeAgo prefix="Last updated " timestamp={event?.startDatetime} />
            <div className="row d-flex justify-content-between">
              <div className="col-auto">
                <p className="my-1">
                  <i className="bi bi-person-lock"></i> {event?.capacity} |{" "}
                  <i className="bi bi-pin-map-fill"></i> {event?.location?.city}
                  , {event?.location?.country} |{" "}
                  <TimeAgo prefix="Start: " timestamp={event?.startDatetime} />{" "}
                  | <TimeAgo prefix="End: " timestamp={event?.endDatetime} />
                </p>
              </div>
              <div className="col-auto">
                {/* buttons -----------------------------------------------*/}
                <div className="row d-flex justify-content-between">
                  <div className="col-auto">
                    <Link to={"/events/" + event?.id}>
                      <button
                        type="button"
                        className="btn btn-sm btn-outline-info"
                      >
                        <i className="bi bi-zoom-in"></i> See details
                      </button>
                    </Link>
                  </div>

                  <div className="col-auto">
                    <div
                      className="btn-group btn-group-sm"
                      role="group"
                      aria-label="Small button group"
                    >
                      <button
                        type="button"
                        onClick={onClickMark}
                        className="btn btn-outline-secondary"
                      >
                        {isMarked === true ? (
                          <i className="bi bi-bookmark-fill text-warning"></i>
                        ) : (
                          <i className="bi bi-bookmark"></i>
                        )}
                      </button>
                    </div>
                  </div>
                </div>
              </div>
            </div>

            <div className="tags">
              <i className="bi bi-tags"> </i>
              {event?.tags?.map((item, index) => {
                return (
                  <button
                    key={index}
                    className="badge text-bg-info bg-opacity-10 text-info border-0 mx-1"
                  >
                    {item?.name + " "}
                    <span className="text-danger"></span>
                  </button>
                );
              })}
            </div>
          </div>
          {/* <div className="card-footer text-muted py-0">
            <TimeAgo prefix="Last updated " timestamp={event?.start_datetime} />
          </div> */}
        </div>
      </div>
    </>
  );
};
export default CardEvent;
