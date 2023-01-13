import { Alert, Snackbar } from "@mui/material";
import type { FC } from "react";

interface MessageAlertProps {
  open: boolean;
  onClose: (event: React.SyntheticEvent | Event, reason?: string) => void;
  message: string;
}
const MessageAlert: FC<MessageAlertProps> = (props) => {
  return (
    <Snackbar
      open={props.open}
      autoHideDuration={6000}
      anchorOrigin={{ vertical: "top", horizontal: "center" }}
      onClose={props.onClose}
      style={{
        display: "flex",
        flexDirection: "column",
        alignItems: "center",
        justifyContent: "center",
        color: "white",
      }}
    >
      <Alert
        style={{
          background: "#4285F4",
          color: "white",
          width: "400px",
          height: "50%",
          padding: "0 auto",
          borderRadius: "10px",
        }}
        onClose={props.onClose}
        severity="info"
        sx={{ width: "100%", color: "white" }}
      >
        {props.message}
      </Alert>
    </Snackbar>
  );
};

export default MessageAlert;
