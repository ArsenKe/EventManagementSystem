const Empty = ({ payload }) => {
    let msg = 'No data available!'
    if (payload) {
        msg = payload
    }

    return (
        <h5 className="text-muted text-center border mx-5 my-2">
            {msg}
        </h5>
    );
}
export default Empty