var Volunteer = React.createClass({

  getInitialState: function() {
    return {display: true };
  },
  handleDelete() {
    var self = this;
    $.ajax({
        url: self.props.volunteer._links.self.href,
        type: 'DELETE',
        success: function(result) {
          self.setState({display: false});
        },
        error: function(xhr, ajaxOptions, thrownError) {
          toastr.error(xhr.responseJSON.message);
        }
    });
  },
  render: function() {

    if (this.state.display==false) return null;
    else return (
      <tr>
          <td>{this.props.volunteer.firstName}</td>
          <td>{this.props.volunteer.lastName}</td>
          <td>{this.props.volunteer.email}</td>
          <td>{this.props.volunteer.confirmed}</td>
          <td>
            <button className="btn btn-info" onClick={this.handleDelete}>Delete</button>
          </td>
      </tr>
    );
  }
});

var EmployeeTable = React.createClass({

  render: function() {

    var rows = [];
    this.props.volunteers.forEach(function(volunteer) {
      rows.push(
        <Volunteer volunteer={volunteer} key={volunteer.id} />);
    });

    return (
      <table className="table table-striped">
          <thead>
              <tr>
                  <th>First Name</th>
                  <th>Last Name</th>
                  <th>Email</th>
                  <th>Confirmed</th>
              </tr>
          </thead>
          <tbody>{rows}</tbody>
      </table>
    );
  }
});

var App = React.createClass({

  loadEmployeesFromServer: function() {

    var self = this;
    $.ajax({
        url: "http://192.168.99.100:8701/api/volunteers",
      }).then(function(data) {
        self.setState({ volunteers: data._embedded.volunteers });
      });

  },

  getInitialState: function() {
    return { volunteers: [] };
  },

  componentDidMount: function() {
    this.loadEmployeesFromServer();
  },

  render() {
    return ( <EmployeeTable volunteers={this.state.volunteers} /> );
  }
});

 // VolunteerAdd class. This contains the Modal and Add Volunteer button
var RecipeAdd = React.createClass({
  getInitialState: function() {
    return { showModal: false };
  },
  close: function() {
    globalTitle = "";
    this.setState({ showModal: false });
  },
  open: function() {
    this.setState({ showModal: true });
    if (document.getElementById("title")) {
      $("#title").val(globalTitle);
      if (globalTitle != "") {
        $("#modalTitle").text("Edit Volunteer");
        $("#addButton").text("Edit Volunteer");
      }
    }
    else requestAnimationFrame(this.open);
  },
  add: function() {
    var title = document.getElementById("title").value;
    
    update();
    this.close();
  },
  render: function() {
    return (
      <div>
        <Button
          bsStyle="primary"
          bsSize="large"
          onClick={this.open}
          id="show"
        >
          Add Volunteer
        </Button>
        <Modal show={this.state.showModal} onHide={this.close}>
          <Modal.Header closeButton>
            <Modal.Title id="modalTitle">Add a Volunteer</Modal.Title>
          </Modal.Header>
          <Modal.Body>
            <form>
              <Input type="text" label="First Name" placeholder="First Name" id="title" />
              <Input type="text" label="Last Name" placeholder="Last Name" id="title" />
              <Input type="text" label="Email" placeholder="Email" id="title" />
              <Input type="checkbox" label="Confirmed" placeholder="Confirmed" id="title" />
            </form>
          </Modal.Body>
          <Modal.Footer>
            <Button onClick={this.add} bsStyle="primary" id="addButton">Add Volunteer</Button>
            <Button onClick={this.close}>Close</Button>
          </Modal.Footer>
        </Modal>
      </div>
    );
  }
});
    
    
ReactDOM.render(<App />, document.getElementById('root') );