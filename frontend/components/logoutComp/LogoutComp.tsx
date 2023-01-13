import { Avatar, Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle } from "@mui/material";
import { FC, useEffect, useState } from "react";
import { useDispatch } from "react-redux";
import { logout } from "~store/user/userSlice";
import { useRouter } from "next/router";
import { primaryColor, secondaryColor } from "~style/colors";
import PowerSettingsNewIcon from "@mui/icons-material/PowerSettingsNew";
import PaperComponent from "~components/paperComponent";
import { buttonStyle, LogoutCompContainerStyle, LogoutUserNameStyle, StyledDialigContent } from "./LogoutComp.style";
import Image from "next/image";
interface ILogoutProps {}
const LogoutComp: FC<ILogoutProps> = (props) => {
  const dispatch = useDispatch();
  const router = useRouter();
  const [openConfirmationModel, setOpenConfirmationModel] = useState(false);
  const [buttonYesStyle, setButtonYesStyle] = useState({
    color: "#053158",
    background: "#FFFFFF",
  });
  const [buttonNoStyle, setButtonNoStyle] = useState({
    color: "#053158",
    background: "#FFFFFF",
  });
  const [avatarText, setAvatarText] = useState({
    sx: {
      bgcolor: primaryColor,
    },
    children: <h1></h1>,
  });
  const [fullName, setFullName] = useState<string | null>("");
  const handleClickOpen = () => {
    setOpenConfirmationModel(true);
  };

  const handleCloseConfirmationModel = () => {
    setOpenConfirmationModel(false);
  };
  function stringAvatar(name: string) {
    const fullName = name.split(" ");
    let res = "";
    const n = fullName.length;
    if (n == 0) {
      res = "";
    } else {
      for (let i = 0; i < n; i++) {
        res = `${res}${fullName[i][0]}`;
      }
    }
    return {
      sx: {
        bgcolor: primaryColor,
      },
      children: <div>{res}</div>,
    };
  }
  useEffect(() => {
    const fullNameStored = localStorage.getItem("fullName") || "";
    setAvatarText(stringAvatar(fullNameStored));
    setFullName(fullNameStored);
  }, []);
  const [anchorEl, setAnchorEl] = useState<HTMLElement | null>(null);

  const handleClose = () => {
    setOpenConfirmationModel(false);
  };
  const getFullNameFormated = () => {
    const fullNameArray = fullName?.split(" ");
    if (fullNameArray === undefined) {
      return <h1></h1>;
    } else {
      let res = "";
      const n = fullNameArray.length;
      if (n == 0) {
        res = "";
      } else {
        res = fullNameArray[0];
        for (let i = 1; i < n; i++) {
          res = `${res} ${fullNameArray[i].toUpperCase()}`;
        }
      }
      return <h1 style={LogoutUserNameStyle}>{res}</h1>;
    }
  };
  const open = Boolean(anchorEl);

  const handleLogout = () => {
    dispatch(logout());
    localStorage.removeItem("trello_token");
    localStorage.removeItem("fullName");
    localStorage.removeItem("userId");
    router.push("/");
  };

  return (
    <div style={LogoutCompContainerStyle}>
      <div style={{ display: "flex", flexDirection: "row", gap: "0px 10px", alignItems: "baseline" }}>
        <Avatar {...avatarText} style={{}} />
        {getFullNameFormated()}
      </div>
      <div>
        <div onClick={handleClickOpen}>
          <PowerSettingsNewIcon style={{ fontSize: 40, color: "red", cursor: "pointer" }} />
        </div>
        <Dialog
          open={openConfirmationModel}
          onClose={handleClose}
          PaperComponent={PaperComponent}
          aria-labelledby="draggable-dialog-title"
        >
          <div
            style={{
              width: "560px",
            }}
          >
            <DialogContent style={{ background: "#FFFFFF", borderRadius: "10px" }}>
              <DialogContentText>
                <StyledDialigContent>
                  <Image src="/warning.svg" width={"55.07px"} height={"55.07px"} />
                  <div>Are you sure</div>
                  <div>you want to disconnect ?</div>
                </StyledDialigContent>
              </DialogContentText>
            </DialogContent>
            <DialogActions
              style={{
                display: "flex",
                flexDirection: "row",
                justifyContent: "center",
                alignItems: "center",
                columnGap: "5px",
                marginBottom: "7px",
              }}
            >
              <Button
                sx={{ textTransform: "none" }}
                style={{
                  ...buttonStyle,
                  ...buttonYesStyle,
                }}
                onMouseEnter={() => {
                  setButtonYesStyle({
                    color: "#FFFFFF",
                    background: "#053158",
                  });
                }}
                onMouseLeave={() => {
                  setButtonYesStyle({
                    color: "#053158",
                    background: "#FFFFFF",
                  });
                }}
                onClick={handleLogout}
              >
                Yes
              </Button>
              <Button
                sx={{ textTransform: "none" }}
                style={{
                  ...buttonStyle,
                  ...buttonNoStyle,
                }}
                onMouseEnter={() => {
                  setButtonNoStyle({
                    color: "#FFFFFF",
                    background: "#053158",
                  });
                }}
                onMouseLeave={() => {
                  setButtonNoStyle({
                    color: "#053158",
                    background: "#FFFFFF",
                  });
                }}
                onClick={handleCloseConfirmationModel}
              >
                No
              </Button>
            </DialogActions>
          </div>
        </Dialog>
      </div>
    </div>
  );
};

export default LogoutComp;
