const Error = ({ payload }) => {
    let errorMsg = 'Opps! Something went wrong.'
    if (payload) {
        errorMsg = payload
    }

    // return <div>Error: {error.message}</div>;
    return (
        <div className="container p-4">
            <div className="alert alert-danger" role="alert">
                {errorMsg}
            </div>
        </div>
    );
}
export default Error