<?php

namespace App\Controller;

use App\Entity\Matches;
use App\Entity\Reservation;
use App\Entity\Users;
use App\Form\Reservation1Type;
use App\Services\QrcodeService;
use Doctrine\ORM\EntityManagerInterface;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Dompdf\Dompdf;
use Dompdf\Options;
use Symfony\Component\Routing\Annotation\Route;

/**
 * @Route("/reservation")
 */
class ReservationController extends AbstractController
{
    /**
     * @Route("/", name="app_reservation_index", methods={"GET"})
     */
    public function index(EntityManagerInterface $entityManager): Response
    {
        $reservations = $entityManager
            ->getRepository(Reservation::class)
            ->findAll();

        return $this->render('reservation/index.html.twig', [
            'reservations' => $reservations,
        ]);
    }
    /**
     * @Route("/ticketPDF/{idTicket}", name="app_reservation_showPDF", methods={"GET"})
     */
    public function showPDF(EntityManagerInterface $entityManager,Reservation $reservation, QrcodeService $qrcodeService)
    {
        $qrCode = null;
        // Configure Dompdf according to your needs
        $pdfOptions = new Options();
        $pdfOptions->set('defaultFont', 'Arial');

        // Instantiate Dompdf with our options
        $dompdf = new Dompdf($pdfOptions);

        $qrCode = $qrcodeService->qrcode("/ticketPDF/{$reservation->getIdTicket()}");

        // Retrieve the HTML generated in our twig file
        $html = $this->renderView('reservation/showTicketPDF.html.twig', [
            'reservation' => $reservation,
            'qrCode'=>$qrCode,
            'title' => "Your reservation"
        ]);

        // Load HTML to Dompdf
        $dompdf->loadHtml($html);

        // (Optional) Setup the paper size and orientation 'portrait' or 'portrait'
        $dompdf->setPaper('A4', 'landscape');

        // Render the HTML as PDF
        $dompdf->render();

        // Output the generated PDF to Browser (inline view)
        $dompdf->stream("reservation.pdf", [
            "Attachment" => false
        ]);
    }


    /**
     * @Route("/ticketPDF", name="app_reservation_showNoIdTicket", methods={"GET"})
     */

    public function showNoIdTicket(EntityManagerInterface $entityManager)
    {
        throw $this->createNotFoundException("page not found!!!!!!!!!!");
    }



    /**
     * @Route("/new", name="app_reservation_new", methods={"GET", "POST"})
     */
    public function new(Request $request, EntityManagerInterface $entityManager)
    {
        $reservation = new Reservation();
        $match= $entityManager->find(Matches::class, $request->get('id_match'));
        $user= $entityManager->find(Users::class, $request->get('id_user'));
        $reservation->setIdMatch($match);
        $reservation->setIdUser($user);
        if(true) {
            $entityManager->persist($reservation);
            $entityManager->flush();
            return new JsonResponse(['idTicket'=>$reservation->getIdTicket()]);
            echo ('success');
        }else{

            return new JsonResponse(['result'=>'boo']);
            echo ('failed');
        }
        return"hi";

    }

    /**
     * @Route("/{idTicket}", name="app_reservation_show", methods={"GET"})
     */
    public function show(Reservation $reservation): Response
    {
        return $this->render('reservation/show.html.twig', [
            'reservation' => $reservation,
        ]);
    }

    /**
     * @Route("/{idTicket}/edit", name="app_reservation_edit", methods={"GET", "POST"})
     */
    public function edit(Request $request, Reservation $reservation, EntityManagerInterface $entityManager): Response
    {
        $form = $this->createForm(Reservation1Type::class, $reservation);
        $form->handleRequest($request);

        if ($form->isSubmitted() && $form->isValid()) {
            $entityManager->flush();

            return $this->redirectToRoute('app_reservation_index', [], Response::HTTP_SEE_OTHER);
        }

        return $this->render('reservation/edit.html.twig', [
            'reservation' => $reservation,
            'form' => $form->createView(),
        ]);
    }

    /**
     * @Route("/{idTicket}", name="app_reservation_delete", methods={"POST"})
     */
    public function delete(Request $request, Reservation $reservation, EntityManagerInterface $entityManager): Response
    {
        if ($this->isCsrfTokenValid('delete'.$reservation->getIdTicket(), $request->request->get('_token'))) {
            $entityManager->remove($reservation);
            $entityManager->flush();
        }

        return $this->redirectToRoute('app_reservation_index', [], Response::HTTP_SEE_OTHER);
    }
}
