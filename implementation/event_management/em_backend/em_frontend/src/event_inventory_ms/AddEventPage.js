import { useEffect, useState } from "react";
import { apiCall, toastMessage } from "../utils/utils";
import { ENDPOINTS } from "../utils/statics";
import HeadlineText from "../components/HeadlineText";
import ReactDatePicker from "react-datepicker";
import SelectSearchComponent from "../components/SelectSearchComponent";
import { useLocation, useNavigate } from "react-router-dom";
import { ca } from "date-fns/locale";

function AddEventPage() {
  const navigate = useNavigate();
  const location = useLocation();
  const event = location.state?.event;

  const [eventName, setEventName] = useState(event?.eventName);
  const [description, setDescription] = useState(event?.description);

  const [streetNumber, setStreetNumber] = useState(event?.location?.streetNumber);
  const [street, setStreet] = useState(event?.location?.street);
  const [zip, setZip] = useState(event?.location?.zip);
  const [city, setCity] = useState(event?.location?.city);
  const [country, setCountry] = useState(event?.location?.country);
  const [capacity, setCapacity] = useState(event?.capacity);

  const [tagList, setTagList] = useState();
  const [selectedTagList, setSelectedTagList] = useState(event?.tags);

  const [startDate, setStartDate] = useState(event ? new Date(event?.startDatetime) : new Date());
  const [endDate, setEndDate] = useState(event ? new Date(event?.endDatetime) : new Date());
    
  useEffect(() => {
    fetchTags();
  }, []);

  const fetchTags = async () => {
    let response = await apiCall(ENDPOINTS.tags, "GET");

    console.log(response);
    setTagList(response?.data);
  };

  const addEvent = async (e) => {
    e.preventDefault();

    const requesBody = {
      eventName: eventName,
      description: description,
      startDatetime: startDate,
      endDatetime: endDate,
      location: {
        streetNumber: streetNumber,
        street: street,
        zip: zip,
        city: city,
        country: country,
      },
      capacity: capacity,
      tags: selectedTagList,
    };
    console.log(requesBody);
    let response = await apiCall(ENDPOINTS.addEvent, "POST", requesBody);
  
    console.log(response);
    clearform();
    toastMessage("Event added successfully!");
    navigate(-1);
  };

  const updateEvent = async (e) => {
    e.preventDefault();
    const requesBody = {
      eventName: eventName,
      description: description,
      startDatetime: startDate,
      endDatetime: endDate,
      location: {
        streetNumber: streetNumber,
        street: street,
        zip: zip,
        city: city,
        country: country,
      },
      capacity: capacity,
      tags: selectedTagList,
    };
    console.log(requesBody);
    let response = await apiCall(
      ENDPOINTS.updateEvent(event?.id),
      "PUT",
      requesBody
    );
    console.log(response);
    // clearform();
    toastMessage("Event updated successfully!");
    navigate(-1);
  };

  const clearform = () => {
    setEventName();
    setDescription();

    setStreetNumber();
    setStreet();
    setZip();
    setCity();
    setCountry();
    setCapacity();

    setSelectedTagList();
    setStartDate(new Date());
    setEndDate(new Date());
  };

  return (
    <>
    <p>{event?.eventName}</p>
      <HeadlineText text={event ? "Update Event" : "Add Event"} />
      <form class="row g-3 mb-4 pb-4" onSubmit={event ? updateEvent : addEvent}>
      
        <div class="col-md-12">
          <label class="form-label">Event name</label>
          <input
            type="text"
            class="form-control"
            value={eventName}
            onChange={(e) => setEventName(e.target.value)}
            required
          />
        </div>

        <div class="col-md-12">
          <label class="form-label">Event description</label>
          <textarea
            class="form-control"
            placeholder=""
            style={{ height: "150px" }}
            value={description}
            onChange={(e) => setDescription(e.target.value)}
            required
          ></textarea>
        </div>

        <SelectSearchComponent
          value={selectedTagList}
          onChange={(e) => {
            setSelectedTagList(e);
          }}
          dataList={tagList}
          label={
            <span>
              <i className="bi bi-tags"> </i> Select Tag for the event
            </span>
          }
          multiple={true}
        />

        <div className="row mt-3">
          <div className="col-auto">
            <label class="form-label">Start date</label>
            <ReactDatePicker
              className="form-control"
              selected={startDate}
              value={startDate}
              onChange={(date) => setStartDate(date)}
              required
            />
          </div>
          <div className="col-auto">
            <label class="form-label">End date</label>
            <ReactDatePicker
              className="form-control"
              selected={endDate}
              value={endDate}
              onChange={(date) => setEndDate(date)}
              required
            />
          </div>
          <div class="col-auto">
            <label class="form-label">Capacity</label>
            <input
              type="number"
              class="form-control"
              placeholder="234"
              value={capacity}
              onChange={(e) => setCapacity(e.target.value)}
              required
            />
          </div>
        </div>

        <div class="col-8">
          <label class="form-label">Street name</label>
          <input
            type="text"
            class="form-control"
            placeholder="Main St"
            value={street}
            onChange={(e) => setStreet(e.target.value)}
            required
          />
        </div>
        <div class="col-md-4">
          <label class="form-label">Street number</label>
          <input
            type="number"
            class="form-control"
            placeholder="12"
            value={streetNumber}
            onChange={(e) => setStreetNumber(e.target.value.toString())}
            required
          />
        </div>

        <div class="col-md-6">
          <label class="form-label">City</label>
          <input
            type="text"
            class="form-control"
            value={city}
            onChange={(e) => setCity(e.target.value)}
            required
          />
        </div>

        <div class="col-md-2">
          <label class="form-label">Zip</label>
          <input
            type="number"
            class="form-control"
            placeholder="1209"
            value={zip}
            onChange={(e) => setZip(e.target.value.toString())}
            required
          />
        </div>
        <div class="col-md-4">
          <label class="form-label">Country</label>
          <input
            type="text"
            class="form-control"
            value={country}
            onChange={(e) => setCountry(e.target.value)}
            required
          />
        </div>

        <div class="col-12 text-end">
          <button type="submit" class="btn btn-info text-white">
            {event ? "Update event" : "Add event"}
          </button>
        </div>
      </form>
    </>
  );
}

export default AddEventPage;
