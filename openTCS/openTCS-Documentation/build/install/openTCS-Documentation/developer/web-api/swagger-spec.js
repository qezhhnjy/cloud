window.swaggerSpec={
  "openapi" : "3.0.0",
  "info" : {
    "description" : "Bodies of HTTP requests and responses, where applicable, are JSON structures. The encoding used may be UTF-8, UTF-16 or UTF-32. Where time stamps are used, they are encoded using [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601); the time zone used is UTC.\n\nThe TCP port to be used for the HTTP requests is configuration-dependent; by default, it is 55200.\n\nBy default, requests are accepted without requiring any authentication. Optionally, an access key can be set in the kernel configuration. The configured value is then expected to be sent by the client in an HTTP header named `X-Api-Access-Key`.",
    "version" : "1.0.0",
    "title" : "openTCS web API specification"
  },
  "servers" : [ {
    "url" : "http://localhost:55200/v1",
    "description" : "openTCS kernel running on localhost"
  } ],
  "tags" : [ {
    "name" : "Transport orders",
    "description" : "Working with transport orders"
  }, {
    "name" : "Vehicles",
    "description" : "Working with vehicles"
  }, {
    "name" : "Status",
    "description" : "Retrieving status updates"
  } ],
  "security" : [ {
    "ApiKeyAuth" : [ ]
  } ],
  "paths" : {
    "/transportOrders" : {
      "get" : {
        "tags" : [ "Transport orders" ],
        "summary" : "Retrieves a set of transport orders.",
        "description" : "",
        "parameters" : [ {
          "name" : "intendedVehicle",
          "in" : "query",
          "description" : "The name of the vehicle that is intended to process the transport orders to be retrieved.",
          "required" : false,
          "schema" : {
            "type" : "string",
            "default" : null
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "Successful response",
            "content" : {
              "application/json" : {
                "schema" : {
                  "title" : "ArrayOfTransportOrders",
                  "type" : "array",
                  "items" : {
                    "$ref" : "#/components/schemas/TransportOrderState"
                  }
                }
              }
            }
          },
          "404" : {
            "description" : "Referencing object that could not be found.",
            "content" : {
              "application/json" : {
                "schema" : {
                  "type" : "array",
                  "items" : {
                    "type" : "string",
                    "description" : "Details on the actual error.",
                    "example" : "Could not find the intended vehicle 'Vehicle-0001'."
                  }
                }
              }
            }
          }
        }
      }
    },
    "/transportOrders/{NAME}" : {
      "get" : {
        "tags" : [ "Transport orders" ],
        "summary" : "Retrieves a single named transport order.",
        "description" : "",
        "parameters" : [ {
          "name" : "NAME",
          "in" : "path",
          "description" : "The name of the transport order to be retrieved.",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "Successful operation",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/TransportOrderState"
                }
              }
            }
          },
          "404" : {
            "description" : "Referencing object that could not be found.",
            "content" : {
              "application/json" : {
                "schema" : {
                  "type" : "array",
                  "items" : {
                    "type" : "string",
                    "description" : "Details on the actual error.",
                    "example" : "Could not find transport order 'TOrder-01'."
                  }
                }
              }
            }
          }
        }
      },
      "post" : {
        "tags" : [ "Transport orders" ],
        "summary" : "Creates a new transport order with the given name.",
        "description" : "",
        "responses" : {
          "200" : {
            "description" : "Successful operation"
          },
          "404" : {
            "description" : "Referencing object that could not be found.",
            "content" : {
              "application/json" : {
                "schema" : {
                  "type" : "array",
                  "items" : {
                    "type" : "string",
                    "description" : "Details on the actual error.",
                    "example" : "Could not find location 'Storage 01'."
                  }
                }
              }
            }
          },
          "409" : {
            "description" : "An object with the same name already exists in the model.",
            "content" : {
              "application/json" : {
                "schema" : {
                  "type" : "array",
                  "items" : {
                    "type" : "string",
                    "description" : "Details on the actual error.",
                    "example" : "Transport order 'TOrder-01' already exists."
                  }
                }
              }
            }
          },
          "500" : {
            "description" : "Unexpectedly interrupted or there was an exception in the kernel while executing this method.",
            "content" : {
              "application/json" : {
                "schema" : {
                  "type" : "array",
                  "items" : {
                    "type" : "string",
                    "description" : "Details on the actual error."
                  }
                }
              }
            }
          }
        },
        "parameters" : [ {
          "name" : "NAME",
          "in" : "path",
          "description" : "The name of the transport order to be created.",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "requestBody" : {
          "content" : {
            "application/json" : {
              "schema" : {
                "$ref" : "#/components/schemas/TransportOrder"
              }
            }
          },
          "description" : "The details of the transport order to be created."
        }
      }
    },
    "/transportOrders/{NAME}/withdrawal" : {
      "post" : {
        "tags" : [ "Transport orders" ],
        "summary" : "Withdraws the transport order with the given name.",
        "description" : "",
        "parameters" : [ {
          "name" : "NAME",
          "in" : "path",
          "description" : "The name of the transport order to be withdrawn.",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        }, {
          "name" : "immediate",
          "in" : "query",
          "description" : "Whether the transport order should be aborted as quickly as possible.",
          "required" : false,
          "schema" : {
            "type" : "boolean",
            "default" : false
          }
        }, {
          "name" : "disableVehicle",
          "in" : "query",
          "description" : "Deprecated, explicitly set the vehicle's integration level, instead.",
          "required" : false,
          "deprecated" : true,
          "schema" : {
            "type" : "boolean",
            "default" : false
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "Successful operation"
          },
          "404" : {
            "description" : "Referencing object that could not be found.",
            "content" : {
              "application/json" : {
                "schema" : {
                  "type" : "array",
                  "items" : {
                    "type" : "string",
                    "description" : "Details on the actual error.",
                    "example" : "Could not find transport order 'TOrder-01'."
                  }
                }
              }
            }
          }
        }
      }
    },
    "/vehicles" : {
      "get" : {
        "tags" : [ "Vehicles" ],
        "summary" : "Retrieves a set of vehicles.",
        "description" : "",
        "parameters" : [ {
          "name" : "procState",
          "in" : "query",
          "description" : "The processing state of the vehicles to be retrieved.",
          "example" : "IDLE",
          "required" : false,
          "schema" : {
            "type" : "string",
            "default" : null,
            "enum" : [ "UNAVAILABLE", "IDLE", "AWAITING_ORDER", "PROCESSING_ORDER" ]
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "Successful response",
            "content" : {
              "application/json" : {
                "schema" : {
                  "title" : "ArrayOfVehicles",
                  "type" : "array",
                  "items" : {
                    "$ref" : "#/components/schemas/VehicleState"
                  }
                }
              }
            }
          }
        }
      }
    },
    "/vehicles/{NAME}" : {
      "get" : {
        "tags" : [ "Vehicles" ],
        "summary" : "Retrieves the vehicle with the given name.",
        "description" : "",
        "parameters" : [ {
          "name" : "NAME",
          "in" : "path",
          "description" : "The name of the vehicle to be retrieved.",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "Successful operation",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/VehicleState"
                }
              }
            }
          },
          "404" : {
            "description" : "Referencing object that could not be found.",
            "content" : {
              "application/json" : {
                "schema" : {
                  "type" : "array",
                  "items" : {
                    "type" : "string",
                    "description" : "Details on the actual error.",
                    "example" : "Could not find vehicle 'Vehicle-0001'."
                  }
                }
              }
            }
          }
        }
      }
    },
    "/vehicles/{NAME}/withdrawal" : {
      "post" : {
        "tags" : [ "Vehicles" ],
        "summary" : "Withdraws a transport order processed by the vehicle with the given name.",
        "description" : "",
        "parameters" : [ {
          "name" : "NAME",
          "in" : "path",
          "description" : "Name of the vehicle processing the transport order to be withdrawn",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        }, {
          "name" : "immediate",
          "in" : "query",
          "description" : "Whether the transport order should be aborted as quickly as possible.",
          "required" : false,
          "schema" : {
            "type" : "boolean",
            "default" : false
          }
        }, {
          "name" : "disableVehicle",
          "in" : "query",
          "description" : "Deprecated, explicitly set the vehicle's integration level, instead.",
          "required" : false,
          "deprecated" : true,
          "schema" : {
            "type" : "boolean",
            "default" : false
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "Successful operation"
          },
          "404" : {
            "description" : "Referencing object that could not be found.",
            "content" : {
              "application/json" : {
                "schema" : {
                  "type" : "array",
                  "items" : {
                    "type" : "string",
                    "description" : "Details on the actual error.",
                    "example" : "Could not find vehicle 'Vehicle-0001'."
                  }
                }
              }
            }
          }
        }
      }
    },
    "/vehicles/{NAME}/integrationLevel" : {
      "put" : {
        "tags" : [ "Vehicles" ],
        "summary" : "Sets a new integration level for the named vehicle.",
        "description" : "",
        "parameters" : [ {
          "name" : "NAME",
          "in" : "path",
          "description" : "The name of the vehicle.",
          "required" : true,
          "schema" : {
            "type" : "string"
          }
        }, {
          "name" : "newValue",
          "in" : "query",
          "description" : "The vehicle's new integration level.",
          "required" : true,
          "example" : "TO_BE_RESPECTED",
          "schema" : {
            "type" : "string",
            "enum" : [ "TO_BE_UTILIZED", "TO_BE_RESPECTED", "TO_BE_NOTICED", "TO_BE_IGNORED" ]
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "Successful operation"
          },
          "404" : {
            "description" : "Referencing object that could not be found.",
            "content" : {
              "application/json" : {
                "schema" : {
                  "type" : "array",
                  "items" : {
                    "type" : "string",
                    "description" : "Details on the actual error.",
                    "example" : "Could not find vehicle 'Vehicle-0001'."
                  }
                }
              }
            }
          }
        }
      }
    },
    "/events" : {
      "get" : {
        "tags" : [ "Status" ],
        "summary" : "Retrieves a list of events.",
        "description" : "This operation uses *long polling* to avoid excessive load on the server: Set the *timeout* parameter to a value that indicates how long the operation may wait if there currently aren't any events to be returned.",
        "parameters" : [ {
          "name" : "minSequenceNo",
          "in" : "query",
          "description" : "The minimum sequence number of events to be retrieved. Can/Should be used to filter out events that have already been retrieved. (Set this to the maximum sequence number already seen, incremented by 1.)",
          "required" : false,
          "schema" : {
            "type" : "integer",
            "format" : "int64",
            "default" : 0
          }
        }, {
          "name" : "maxSequenceNo",
          "in" : "query",
          "description" : "The maximum sequence number of events to be retrieved. Can/Should be used to limit the number of events retrieved. (Set this to e.g. *minSequenceNo* + 100.)",
          "required" : false,
          "schema" : {
            "type" : "integer",
            "format" : "int64",
            "default" : "9223372036854775807"
          }
        }, {
          "name" : "timeout",
          "in" : "query",
          "description" : "The time (in milliseconds) to wait for events to arrive if there currently are not any events to be returned. May not be greater than 10000.",
          "required" : false,
          "schema" : {
            "type" : "integer",
            "format" : "int64",
            "default" : 1000
          }
        } ],
        "responses" : {
          "200" : {
            "description" : "Successful response",
            "content" : {
              "application/json" : {
                "schema" : {
                  "$ref" : "#/components/schemas/StatusMessageList"
                }
              }
            }
          },
          "400" : {
            "description" : "Invalid parameter value(s).",
            "content" : {
              "application/json" : {
                "schema" : {
                  "type" : "array",
                  "items" : {
                    "type" : "string",
                    "description" : "Details on the actual error.",
                    "example" : "Parameter 'timeout' is not in the correct range."
                  }
                }
              }
            }
          }
        }
      }
    }
  },
  "components" : {
    "securitySchemes" : {
      "ApiKeyAuth" : {
        "type" : "apiKey",
        "in" : "header",
        "name" : "X-Api-Access-Key"
      }
    },
    "schemas" : {
      "TransportOrderState" : {
        "title" : "Transport Order State",
        "type" : "object",
        "additionalProperties" : false,
        "properties" : {
          "name" : {
            "type" : "string",
            "description" : "The name of the transport order.",
            "example" : "TOrder-01"
          },
          "category" : {
            "type" : "string",
            "description" : "The category of the transport order.",
            "example" : "Park"
          },
          "state" : {
            "type" : "string",
            "enum" : [ "RAW", "ACTIVE", "DISPATCHABLE", "BEING_PROCESSED", "WITHDRAWN", "FINISHED", "FAILED", "UNROUTABLE" ],
            "description" : "The transport order's current state."
          },
          "intendedVehicle" : {
            "type" : "string",
            "description" : "The name of the vehicle that is intended to process the transport order.",
            "example" : "Vehicle-0001"
          },
          "processingVehicle" : {
            "type" : "string",
            "description" : "The name of the vehicle currently processing the transport order.",
            "example" : "Vehicle-0002"
          },
          "destinations" : {
            "type" : "array",
            "items" : {
              "$ref" : "#/components/schemas/DestinationState"
            },
            "description" : "The sequence of destinations of the transport order."
          }
        }
      },
      "DestinationState" : {
        "type" : "object",
        "additionalProperties" : false,
        "properties" : {
          "locationName" : {
            "type" : "string",
            "description" : "The name of the destination location",
            "example" : "Storage-01"
          },
          "operation" : {
            "type" : "string",
            "description" : "The destination operation",
            "example" : "Store"
          },
          "state" : {
            "type" : "string",
            "enum" : [ "PRISTINE", "ACTIVE", "TRAVELLING", "OPERATING", "FINISHED", "FAILED" ],
            "description" : "The drive order's state"
          },
          "properties" : {
            "type" : "array",
            "minItems" : 0,
            "maxItems" : 2147483647,
            "items" : {
              "$ref" : "#/components/schemas/Property"
            },
            "description" : "The drive order's properties"
          }
        },
        "required" : [ "locationName", "operation", "state" ]
      },
      "TransportOrder" : {
        "title" : "Transport Order",
        "type" : "object",
        "additionalProperties" : false,
        "properties" : {
          "deadline" : {
            "type" : "string",
            "format" : "date-time",
            "description" : "The (optional) deadline of the transport order",
            "example" : "2018-05-17T06:42:40.396Z"
          },
          "intendedVehicle" : {
            "type" : "string",
            "description" : "The (optional) intended vehicle of the transport order",
            "example" : "Vehicle-01"
          },
          "destinations" : {
            "type" : "array",
            "minItems" : 1,
            "maxItems" : 2147483647,
            "items" : {
              "$ref" : "#/components/schemas/DestinationOrder"
            },
            "description" : "The destinations"
          },
          "properties" : {
            "type" : "array",
            "minItems" : 0,
            "maxItems" : 2147483647,
            "items" : {
              "$ref" : "#/components/schemas/Property"
            },
            "description" : "The transport order's properties"
          },
          "dependencies" : {
            "type" : "array",
            "minItems" : 0,
            "maxItems" : 2147483647,
            "items" : {
              "type" : "string",
              "example" : "TOrder-002"
            },
            "description" : "The transport order's dependencies"
          }
        },
        "required" : [ "destinations" ]
      },
      "DestinationOrder" : {
        "type" : "object",
        "additionalProperties" : false,
        "properties" : {
          "locationName" : {
            "type" : "string",
            "description" : "The name of the destination location",
            "example" : "Storage 01"
          },
          "operation" : {
            "type" : "string",
            "description" : "The destination operation",
            "example" : "Load cargo"
          },
          "properties" : {
            "type" : "array",
            "minItems" : 0,
            "maxItems" : 2147483647,
            "items" : {
              "$ref" : "#/components/schemas/Property"
            },
            "description" : "The drive order's properties"
          }
        },
        "required" : [ "locationName", "operation" ]
      },
      "VehicleState" : {
        "title" : "Vehicle State",
        "type" : "object",
        "additionalProperties" : false,
        "properties" : {
          "name" : {
            "type" : "string",
            "description" : "The name of the vehicle",
            "example" : "Vehicle-0001"
          },
          "properties" : {
            "type" : "object",
            "additionalProperties" : {
              "type" : "string"
            },
            "description" : "A set of properties (key-value pairs) associated with this object."
          },
          "length" : {
            "type" : "integer",
            "description" : "The vehicle's length (in mm).",
            "example" : "1000"
          },
          "energyLevelGood" : {
            "type" : "integer",
            "description" : "The value at/above which the vehicle's energy level is considered 'good'.",
            "example" : "90"
          },
          "energyLevelCritical" : {
            "type" : "integer",
            "description" : "The value at/below which the vehicle's energy level is considered 'critical'.",
            "example" : "30"
          },
          "energyLevel" : {
            "type" : "integer",
            "description" : "The vehicle's remaining energy (in percent of the maximum).",
            "example" : "60"
          },
          "integrationLevel" : {
            "type" : "string",
            "enum" : [ "TO_BE_IGNORED", "TO_BE_NOTICED", "TO_BE_RESPECTED", "TO_BE_UTILIZED" ],
            "description" : "The vehicle's integration level."
          },
          "procState" : {
            "type" : "string",
            "enum" : [ "UNAVAILABLE", "IDLE", "AWAITING_ORDER", "PROCESSING_ORDER" ],
            "description" : "The vehicle's current processing state."
          },
          "transportOrder" : {
            "type" : "string",
            "description" : "The name of the transport order the vehicle is currently processing.",
            "example" : "TOrder-01"
          },
          "currentPosition" : {
            "type" : "string",
            "description" : "The name of the point which the vehicle currently occupies.",
            "example" : "Point-0001"
          },
          "state" : {
            "type" : "string",
            "enum" : [ "UNKNOWN", "UNAVAILABLE", "ERROR", "IDLE", "EXECUTING", "CHARGING" ],
            "description" : "The vehicle's current state."
          }
        },
        "required" : [ "length", "energyLevelGood", "energyLevelCritical", "energyLevel" ]
      },
      "StatusMessageList" : {
        "title" : "Status Message List",
        "type" : "object",
        "additionalProperties" : false,
        "properties" : {
          "timeStamp" : {
            "type" : "string",
            "format" : "date-time",
            "description" : "The point of time at which this data structure was created"
          },
          "statusMessages" : {
            "type" : "array",
            "items" : {
              "oneOf" : [ {
                "$ref" : "#/components/schemas/OrderStatusMessage"
              }, {
                "$ref" : "#/components/schemas/VehicleStatusMessage"
              } ]
            },
            "description" : "The status messages"
          }
        },
        "required" : [ "timeStamp", "statusMessages" ]
      },
      "StatusMessage" : {
        "title" : "AbstractStatusMessage",
        "type" : "object",
        "properties" : {
          "type" : {
            "type" : "string",
            "enum" : [ "OrderStatusMessage", "VehicleStatusMessage" ]
          },
          "sequenceNumber" : {
            "type" : "integer",
            "description" : "The (unique) sequence number of this status message",
            "example" : "123"
          },
          "creationTimeStamp" : {
            "type" : "string",
            "format" : "date-time",
            "description" : "When this status message was created",
            "example" : "2018-05-14T07:42:00.343Z"
          }
        },
        "discriminator" : {
          "propertyName" : "type"
        },
        "required" : [ "type", "sequenceNumber", "creationTimeStamp" ]
      },
      "OrderStatusMessage" : {
        "title" : "OrderStatusMessage",
        "type" : "object",
        "additionalProperties" : false,
        "allOf" : [ {
          "$ref" : "#/components/schemas/StatusMessage"
        }, {
          "properties" : {
            "type" : {
              "type" : "string",
              "enum" : [ "TransportOrder" ],
              "default" : "TransportOrder"
            },
            "orderName" : {
              "type" : "string",
              "description" : "The (optional) transport order name",
              "example" : "TOrder-0001"
            },
            "processingVehicleName" : {
              "type" : "string",
              "description" : "The processing vehicle's name",
              "example" : "Vehicle-0001"
            },
            "orderState" : {
              "type" : "string",
              "enum" : [ "RAW", "ACTIVE", "DISPATCHABLE", "BEING_PROCESSED", "WITHDRAWN", "FINISHED", "FAILED", "UNROUTABLE" ],
              "description" : "The transport order's current state"
            },
            "destinations" : {
              "type" : "array",
              "minItems" : 1,
              "maxItems" : 2147483647,
              "items" : {
                "$ref" : "#/components/schemas/DestinationState"
              },
              "description" : "The transport order's destinations"
            },
            "properties" : {
              "type" : "array",
              "items" : {
                "$ref" : "#/components/schemas/Property"
              },
              "description" : "The transport order's properties"
            }
          }
        } ],
        "required" : [ "type" ]
      },
      "VehicleStatusMessage" : {
        "type" : "object",
        "additionalProperties" : false,
        "allOf" : [ {
          "$ref" : "#/components/schemas/StatusMessage"
        }, {
          "properties" : {
            "type" : {
              "type" : "string",
              "enum" : [ "Vehicle" ],
              "default" : "Vehicle"
            },
            "vehicleName" : {
              "type" : "string",
              "description" : "The vehicle's name",
              "example" : "Vehicle-0001"
            },
            "transportOrderName" : {
              "type" : "string",
              "description" : "The name of the transport order the vehicle currently processes",
              "example" : "TOrder-0001"
            },
            "position" : {
              "type" : "string",
              "description" : "The name of the point the vehicle currently occupies",
              "example" : "Point-0001"
            },
            "precisePosition" : {
              "$ref" : "#/components/schemas/PrecisePosition"
            },
            "state" : {
              "type" : "string",
              "enum" : [ "UNKNOWN", "UNAVAILABLE", "ERROR", "IDLE", "EXECUTING", "CHARGING" ],
              "description" : "The vehicle's current state"
            },
            "procState" : {
              "type" : "string",
              "enum" : [ "UNAVAILABLE", "IDLE", "AWAITING_ORDER", "PROCESSING_ORDER" ],
              "description" : "The vehicle's current processing state"
            }
          }
        } ],
        "title" : "VehicleStatusMessage",
        "required" : [ "type", "vehicleName", "state", "procState" ]
      },
      "Property" : {
        "type" : "object",
        "additionalProperties" : false,
        "properties" : {
          "key" : {
            "type" : "string",
            "description" : "The property's key",
            "example" : "key1"
          },
          "value" : {
            "type" : "string",
            "description" : "The property's value",
            "example" : "value1"
          }
        },
        "required" : [ "key", "value" ]
      },
      "PrecisePosition" : {
        "type" : "object",
        "additionalProperties" : false,
        "properties" : {
          "x" : {
            "type" : "integer",
            "description" : "The position's X coordinate",
            "example" : "60"
          },
          "y" : {
            "type" : "integer",
            "description" : "The position's Y coordinate",
            "example" : "40"
          },
          "z" : {
            "type" : "integer",
            "description" : "The position's Z coordinate",
            "example" : "0"
          }
        },
        "required" : [ "x", true, "z" ]
      }
    }
  }
}