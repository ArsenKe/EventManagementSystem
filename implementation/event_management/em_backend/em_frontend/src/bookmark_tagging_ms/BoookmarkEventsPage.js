import { useEffect, useState } from "react";
import CardEvent from "../components/CardEvent";
import Empty from "../components/Empty";
import { apiCall, loggedUser, toastMessage } from "../utils/utils";
import { ENDPOINTS } from "../utils/statics";
import HeadlineText from "../components/HeadlineText";

function BoookmarkEventsPage() {
  const [eventList, setEventList] = useState();
  const [bookmarkList, setBookmarkList] = useState();
  const [recommendationList, setRecommendationList] = useState();

  useEffect(() => {
    fetchBookmarks();
    fetchRecommendation();
  }, []);

  const fetchBookmarks = async () => {
    let eventAllresponse = await apiCall(ENDPOINTS.events, "GET");
    console.log(eventAllresponse);
    // setEventList(response?.data);

    let bookmarkAllresponse = await apiCall(
      ENDPOINTS.bookmarks(loggedUser?.id),
      "GET"
    );
    console.log(bookmarkAllresponse);
    let modifiedEventList = [];
    for (let event of eventAllresponse?.data) {
      for (let bmark of bookmarkAllresponse?.data) {
        if (event.id === bmark.eventId) {
          modifiedEventList.push({
            id: bmark.id,
            event: event,
            user: { id: bmark.userId },
          });
        }
      }
    }
    console.log(modifiedEventList);
    setEventList(modifiedEventList);
  };

  const unMarkEvent = async (bookmark_id) => {
    await apiCall(ENDPOINTS.unMarkEvent(bookmark_id), "DELETE");
    fetchBookmarks();
    toastMessage("Event removed from bookmark list");
    fetchRecommendation();
  };

  const fetchRecommendation = async () => {
    let response = await apiCall(
      ENDPOINTS.recommendationList(loggedUser?.id),
      "GET"
    );

    // console.log(response);
    setRecommendationList(response);
  };

  const markEvent = async (user_id, event_id) => {
    const requesBody = {
      eventId: event_id,
      userId: user_id
    };
    console.log(requesBody);
    let response = await apiCall(
      ENDPOINTS.markEvent,
      "POST",
      requesBody
    );
    console.log(response);
    fetchBookmarks();
    toastMessage("Event added to Bookmark successfully!");
    fetchRecommendation();
  };

  return (
    <>
      <div className="col-8 row  d-flex justify-content-between mt-2">
        <div className="col-auto">
          <HeadlineText text="Bookmarks" />
        </div>
        <div className="col-auto">
          {/* export button----------------- */}
          <div className="dropdown">
            <button
              className="btn btn-sm btn-info bg-info bg-opacity-10 dropdown-toggle"
              type="button"
              data-bs-toggle="dropdown"
              aria-expanded="false"
            >
              <i className="bi bi-upload"></i> Export all
            </button>
            <ul className="dropdown-menu">
              <li>
                <a className="dropdown-item" href="#">
                  JSON
                </a>
              </li>
              <li>
                <a className="dropdown-item" href="#">
                  ICS
                </a>
              </li>
              <li>
                <a className="dropdown-item" href="#">
                  XML
                </a>
              </li>
            </ul>
          </div>
        </div>
      </div>

      <div className="row mb-4 pb-4">
        <div className="col-md-8">
          {(eventList?.length ?? 0) < 1 && <Empty />}
          <div className="row">
            {eventList?.map((item, index) => {
              return (
                <CardEvent
                  key={index}
                  event={item?.event}
                  onClickMark={() => unMarkEvent(item?.id)}
                  isMarked={true}
                  isCompact={true}
                />
              );
            })}
          </div>
        </div>
        {/* Recommended----------------------------------------------- */}
        <div className="col-md-4">
          <div className="text-info p-1 my-1 h4 border border-info bg-info bg-opacity-10 border-0 border-bottom border-2 rounded">
            Recommended
          </div>
          {(recommendationList?.length ?? 0) < 1 && <Empty />}
          <div className="row">
            {recommendationList?.map((item, index) => {
              return (
                <CardEvent
                  key={index}
                  event={item}
                  isCompact={true}
                  onClickMark={() =>
                    item?.isMarked === true
                      ? toastMessage("Already marked!")
                      : markEvent(loggedUser?.id, item?.id)
                  }
                />
              );
            })}
          </div>
        </div>
      </div>
    </>
  );
}

export default BoookmarkEventsPage;
