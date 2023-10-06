import React from "react";
import Select from "react-select";

const SelectSearchComponent = ({
  label,
  value,
  onChange,
  dataList,
  onClear,
  disabled = false,
  required = false,
  //   isFloating = false,
  valueAs = "id", //_id/ _key/ name
  multiple = false,
}) => {
  return (
    <div className="my-3">
      <label className="form-label">
        {label}
        <span className="text-danger">{required === true && " *"}</span>
      </label>
      <Select
        // id={label?.trim()}
        className="basic-single"
        classNamePrefix={label ?? "select"}
        //   defaultValue={colourOptions[0]}
        value={value}
        onChange={onChange}
        isDisabled={disabled}
        getOptionLabel={(option) => option?.name}
        getOptionValue={(option) => option?.id}
        isMulti={multiple}
        //   isLoading={isLoading}
        isClearable={true}
        // isRtl={true}
        isSearchable={true}
        // name="_id"
        options={dataList}
      />
    </div>
  );
};

export default SelectSearchComponent;
