// import { parseISO, formatDistanceToNow } from 'date-fns';
import {format, formatDistance} from 'date-fns'

const TimeAgo = ({ prefix, timestamp }) => {
    // console.log(timestamp);
    let timeAgo = ''
    if (timestamp != null && timestamp != undefined) {
        let timestampISO = new Date(timestamp);
        timeAgo = formatDistance(timestampISO, new Date(), { addSuffix: true })
    }

    // formatDistance(subDays(new Date(), 3), new Date(), { addSuffix: true })
    return (
        <span className="text-muted" title={timestamp}>
            <small><i>{prefix} {timeAgo}</i></small>
        </span>
    )
}
export default TimeAgo


function DateTimeFormated(timestamp, withTime) {
    if (timestamp == null || timestamp?.toString()?.length < 1) {
        return "";
    }
    // const rawDate =  Date.now();
    let timestampISO = new Date(timestamp)
    let date;
    if(withTime){
        // date = rawDate.toLocaleDateString() + " " + rawDate.toLocaleTimeString();
        date = format(timestampISO, 'dd.MM.yyyy  HH:mm');
    }else{
        date = date = format(timestampISO, 'dd.MM.yyyy');;
    }
    
    return date;
}

export { TimeAgo, DateTimeFormated }